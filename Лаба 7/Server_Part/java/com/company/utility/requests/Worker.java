package com.company.utility.requests;

import com.company.commandPattern.User;
import com.company.memory.Clients;
import com.company.utility.Log;
import com.company.utility.database.SQL;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Worker implements Runnable {

    private final Log log = new Log();

    private final ServerSocketChannel ss;
    private final SQL sql;
    private final Selector selector;
    private final Clients clients;
    private final User user;

    private final ExecutorService readThreads = Executors.newFixedThreadPool(Clients.MAX_USERS_COUNT);
    private final ExecutorService executeThreads = Executors.newCachedThreadPool();
    private final ExecutorService writeThreads = Executors.newCachedThreadPool();

    public Worker(SQL sql, int PORT, Clients clients, User user) throws IOException {
        this.sql = sql;
        this.clients = clients;
        this.user = user;

        this.ss = ServerSocketChannel.open();
        this.ss.bind(new InetSocketAddress(PORT));
        this.ss.configureBlocking(false);

        this.selector = Selector.open();
        this.ss.register(selector, SelectionKey.OP_ACCEPT);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                if (selector.isOpen()) {
                    if (selector.select() > 0) {
                        Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                        while (iterator.hasNext()) {
                            SelectionKey key = iterator.next();
                            iterator.remove();
                            if (key.isValid()) {
                                if (key.isAcceptable()) accept();
                                else if (key.isReadable() && key.channel() != null && key.channel().isOpen())
                                    readThreads.execute(new Read(key, sql, writeThreads, executeThreads, clients, user));
                            }
                        }
                    }
                }
            }
            readThreads.shutdownNow();
            writeThreads.shutdownNow();
            executeThreads.shutdownNow();
        } catch (IOException | CancelledKeyException e) {
            log.exception(e);
        }
    }

    private void accept() {
        try {
            SocketChannel socket = ss.accept();
            if (socket != null) {
                socket.configureBlocking(false);
                socket.register(selector, SelectionKey.OP_READ);
            }
        } catch (IOException e) {
            log.exception(e);
        }
    }

}
