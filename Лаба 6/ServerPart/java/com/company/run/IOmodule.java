package com.company.run;

import com.company.commandPattern.CommandsOperator;
import com.company.data.io.Answer;
import com.company.data.io.Mail;
import com.company.utility.Log;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class IOmodule implements Runnable {

    private final Log log = new Log();

    private final int PORT;
    private final CommandsOperator operator;
    private Mail mail;


    public IOmodule(int PORT, CommandsOperator operator) {
        this.PORT = PORT;
        this.operator = operator;
    }

    @Override
    public void run() {
        try {
            int trigger = 1;
            int mailSize = -1;

            ServerSocketChannel ss = ServerSocketChannel.open();
            ss.bind(new InetSocketAddress(PORT));
            ss.configureBlocking(false);

            Selector selector = Selector.open();
            ss.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {

                if (selector.isOpen()) {
                    int keys = selector.select();

                    if (keys > 0) {

                        Set<SelectionKey> selectionKeys = selector.selectedKeys();
                        Iterator<SelectionKey> iterator = selectionKeys.iterator();

                        while (iterator.hasNext()) {

                            SelectionKey key = iterator.next();
                            iterator.remove();

                            if (key.isValid()) {
                                if (key.isAcceptable()) {

                                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                                    SocketChannel client = channel.accept();
                                    if (client != null) {
                                        client.configureBlocking(false);
                                        client.register(selector, SelectionKey.OP_READ);
                                    }
                                }
                                if (key.isReadable()) {

                                    SocketChannel client = (SocketChannel) key.channel();
                                    client.configureBlocking(false);

                                    try {

                                        ByteBuffer buffer;

                                        if (trigger == 1) {

                                            buffer = ByteBuffer.allocate(4);
                                            while (buffer.hasRemaining()) client.read(buffer);
                                            try (DataInputStream dis = new DataInputStream(new ByteArrayInputStream(buffer.array().clone()))) {
                                                mailSize = dis.readInt();
                                                if (mailSize > 10000 || mailSize <= 0) {
                                                    key.channel().close();
                                                    key.cancel();
                                                    continue;
                                                }
                                            }
                                            client.register(selector, SelectionKey.OP_READ);
                                            trigger = -1;
                                        } else {

                                            buffer = ByteBuffer.allocate(mailSize);
                                            while (buffer.hasRemaining()) client.read(buffer);

                                            try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buffer.array().clone()))) {
                                                mail = (Mail) ois.readObject();

                                                log.newRequest(mail.getCommandName(), client.getRemoteAddress());
                                                if (mail.isConnect()) log.newConnection(client.getRemoteAddress());
                                            }
                                            client.register(selector, SelectionKey.OP_WRITE);
                                            trigger = 1;
                                            mailSize = -1;
                                        }

                                    } catch (StreamCorruptedException e) {
                                        client.close();
                                        key.cancel();
                                        continue;
                                    } catch (Exception e) {
                                        client.close();
                                        key.cancel();
                                        log.exception(e);
                                        continue;
                                    }
                                }
                                if (key.isWritable()) {

                                    SocketChannel client = (SocketChannel) key.channel();
                                    client.configureBlocking(false);

                                    if (mail == null) {
                                        client.register(selector, SelectionKey.OP_READ);
                                        continue;
                                    }

                                    Answer answer = operator.execute(mail);

                                    try (
                                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                            ObjectOutputStream oos = new ObjectOutputStream(baos)
                                    ) {

                                        oos.writeObject(answer);

                                        ByteBuffer buffer = ByteBuffer.wrap(baos.toByteArray());

                                        while (buffer.hasRemaining()) client.write(buffer);
                                        log.newAnswer(client.getRemoteAddress());

                                        client.register(selector, SelectionKey.OP_READ);
                                        key.channel().close();
                                        key.cancel();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            log.exception(e);
        }
    }
}
