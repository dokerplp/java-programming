package com.company.utility.requests;

import com.company.commandPattern.User;
import com.company.memory.Clients;
import com.company.utility.Log;
import com.company.utility.database.SQL;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;


public class Read implements Runnable {

    private final Log log = new Log();
    private final SQL sql;
    private final ExecutorService writeThreads;
    private final ExecutorService executeThreads;
    private final SelectionKey key;
    private final Clients clients;
    private final User user;

    public Read(SelectionKey key, SQL sql, ExecutorService writeThreads, ExecutorService executeThreads, Clients clients, User user) {
        this.key = key;
        this.sql = sql;
        this.writeThreads = writeThreads;
        this.executeThreads = executeThreads;
        this.clients = clients;
        this.user = user;
    }

    @Override
    public void run() {
        byte[] bytes = {};
        try {
            SocketChannel channel = (SocketChannel) key.channel();
            channel.configureBlocking(false);
            ByteBuffer buffer = ByteBuffer.allocate(10000);

            int b;
            do {
                b = channel.read(buffer);
            } while (b > 0);
            bytes = buffer.array().clone();

        } catch (IOException e) {
            log.exception(e);
        }
        try (
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais)
        ) {
            Object request = ois.readObject();
            executeThreads.execute(new Execute(key, request, sql, writeThreads, clients, user));
        } catch (StreamCorruptedException ignored) {
        } catch (IOException | ClassNotFoundException e) {
            log.exception(e);
        }
    }
}
