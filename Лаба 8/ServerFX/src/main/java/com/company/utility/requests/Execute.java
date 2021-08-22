package com.company.utility.requests;

import com.company.commandPattern.User;
import com.company.data.io.Answer;
import com.company.data.io.Mail;
import com.company.data.io.RequestResult;
import com.company.data.io.Verification;
import com.company.memory.Clients;
import com.company.utility.Log;
import com.company.utility.database.SQL;

import java.nio.channels.SelectionKey;
import java.util.concurrent.ExecutorService;

public class Execute implements Runnable {

    private final Log log = new Log();

    private final SQL sql;

    private final Object read;
    private final SelectionKey key;
    private final ExecutorService writeThreads;
    private final Clients clients;
    private final User user;

    public Execute(SelectionKey key, Object read, SQL sql, ExecutorService writeThreads, Clients clients, User user) {
        this.key = key;
        this.read = read;
        this.sql = sql;
        this.writeThreads = writeThreads;
        this.clients = clients;
        this.user = user;
    }

    @Override
    public void run() {
        Answer answer = null;
        if (read instanceof Mail) answer = mail((Mail) read);
        else if (read instanceof Verification) answer = ver((Verification) read);
        writeThreads.execute(new Write(key, answer));
    }


    private Answer mail(Mail mail) {
        if (mail != null) {
            int id;
            if ((id = clients.contains(mail.getLOGIN(), Verification.hashing(mail.getPASS()))) > 0) {
                log.newRequest(mail.getCommandName(), mail.getLOGIN());
                return user.execute(mail, id);
            } else
                return new Answer("User with such login and password haven't joined server yet", RequestResult.NEED_RECONNECTION);
        }
        return null;
    }

    private Answer ver(Verification ver) {
        if (ver != null) {
            ver.hashing();
            if (ver.isMode()) {
                int user;
                if ((user = sql.userId(ver.getLOGIN(), ver.getPASS())) > 0) {
                    clients.add(ver.getLOGIN(), ver.getPASS(), user);
                    log.join(ver.getLOGIN());
                    return new Answer("Successful sign in", RequestResult.REQUEST_COMPLETED_SUCCESSFULLY);
                } else
                    return new Answer("User with such login and password doesn't exist", RequestResult.REQUEST_FAILED);
            } else {
                int user;
                if ((user = sql.insertUser(ver.getLOGIN(), ver.getPASS())) > 0) {
                    clients.add(ver.getLOGIN(), ver.getPASS(), user);
                    return new Answer("Successful log in", RequestResult.REQUEST_COMPLETED_SUCCESSFULLY);
                } else return new Answer("User with such login already exists", RequestResult.REQUEST_FAILED);
            }
        }
        return null;
    }
}
