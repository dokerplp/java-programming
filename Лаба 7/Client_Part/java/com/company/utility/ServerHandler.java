package com.company.utility;

import com.company.data.io.Answer;
import com.company.data.io.RequestResult;
import com.company.memory.Registers;
import com.company.utility.staticUtil.commands.Show;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ServerHandler {

    public static RequestResult send(Object obj, Socket socket, Registers registers) {

        try (
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(baos)
        ) {
            out.writeObject(obj);
            baos.writeTo(socket.getOutputStream());
        } catch (IOException e) {
            System.err.println("Ошибка отправки запроса на сервер");
            return RequestResult.REQUEST_FAILED;
        }
        try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
            Answer answer = (Answer) ois.readObject();
            if (answer != null) {
                return showAnswer(answer, registers);
            }
            System.err.println("Ошибка получения ответа с сервера");
            return RequestResult.REQUEST_FAILED;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка получения ответа с сервера");
            return RequestResult.REQUEST_FAILED;
        }
    }

    private static RequestResult showAnswer(Answer answer, Registers registers) {
        if (answer == null || answer.getAnswer() == null) {
            System.err.println("Ошибка получения ответа с сервера");
            return RequestResult.REQUEST_FAILED;
        } else if (answer.getAnswer().equals("show command")) {
            System.out.println(Show.execute(answer));
            return RequestResult.REQUEST_COMPLETED_SUCCESSFULLY;
        } else if (answer.getAnswer().equals("update")) {
            registers.switchUpdate();
            if (answer.getProducts() != null && !answer.getProducts().isEmpty())
                registers.setUpdate(answer.getProducts().get(0));
            return RequestResult.REQUEST_COMPLETED_SUCCESSFULLY;
        } else {
            System.out.println(answer.getAnswer());
            return answer.getResult();
        }
    }
}
