package com.company.utility.requests;

import com.company.data.io.Answer;
import com.company.utility.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class Write implements Runnable {

    private final Log log = new Log();

    private final Answer answer;
    private final SelectionKey key;

    public Write(SelectionKey key, Answer answer) {
        this.key = key;
        this.answer = answer;
    }

    @Override
    public void run() {
        try (
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos)
        ) {
            SocketChannel channel = (SocketChannel) key.channel();
            channel.configureBlocking(false);

            oos.writeObject(answer);

            ByteBuffer buffer = ByteBuffer.wrap(baos.toByteArray());
            while (buffer.hasRemaining()) channel.write(buffer);

            key.channel().close();
            key.cancel();

        } catch (IOException | ClassCastException e) {
            log.exception(e);
        }
    }

}
