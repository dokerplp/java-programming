package com.company.utility;

import com.company.data.io.Verification;
import com.company.javaFX.enums.Result;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public class Authorization {

    public int PORT = 2113;
    private String ADDR = "127.0.0.1";

    private String LOGIN;
    private String PASS;

    public String getLOGIN() {
        return LOGIN;
    }
    public String getPASS() {
        return PASS;
    }

    public Authorization(List<String> args) {
        try {
            PORT = args.size() > 0 ? Integer.parseInt(args.get(0)) : 2113;
            ADDR = args.size() > 1 ? args.get(1) : "127.0.0.1";
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {}
    }

    public Socket connect() {
        try {
            return new Socket(InetAddress.getByName(ADDR), PORT);
        } catch (IOException e) {
            return null;
        }
    }

    public Result register(String LOGIN, String PASS, boolean MODE){
        Socket socket = connect();
        if (socket == null) return Result.CANT_CONNECT;
        else {
            this.LOGIN = LOGIN;
            this.PASS = PASS;
            return ServerHandler.send(new Verification(LOGIN, PASS, MODE), socket);
        }
    }
}
