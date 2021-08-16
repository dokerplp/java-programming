package com.company.utility;

import com.company.data.io.Answer;
import com.company.javaFX.ModalWindow;
import com.company.javaFX.enums.Result;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ServerHandler {

    private static final String CANT_SEND = "Can't connect to the server";
    private static final String SEND_ERROR = "Something went wrong while sending request";
    private static final String GET_ERROR = "Something went wrong while getting answer";
    private static final String NEED_RECONNECT = "You need reconnect to the server";

    public static String send(Object obj, Socket socket) {
        try (
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(baos)
        ) {
            out.writeObject(obj);
            baos.writeTo(socket.getOutputStream());
        } catch (IOException e) {
            return CANT_SEND;
        }
        try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
            Answer answer = (Answer) ois.readObject();
            if answer
            return Result.GET_ERROR;
        } catch (IOException | ClassNotFoundException e) {
            return Result.GET_ERROR;
        }
    }
}
