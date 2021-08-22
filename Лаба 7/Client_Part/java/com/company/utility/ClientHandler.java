package com.company.utility;

import com.company.data.io.Mail;
import com.company.exceptions.*;
import com.company.memory.Registers;
import com.company.memory.Vault;
import com.company.utility.staticUtil.Args;

public class ClientHandler {

    private final CommandCreator creator;
    private final Vault vault;
    private final Registers registers;

    public ClientHandler(CommandCreator creator, Vault vault, Registers registers) {
        this.creator = creator;
        this.vault = vault;
        this.registers = registers;
    }

    public Mail mailCreator() {
        Mail mail;
        try {
            if (registers.getUpdateTrigger()) {
                String line = vault.pollOne();
                String com = Args.getCommand(line);
                String arg = Args.getArgument(line);
                if (com == null) return null;
                else if (com.equals("exit")) throw new Exit();
                mail = creator.command(com, arg);
            } else {
                mail = creator.command(registers.getUpdate());
                registers.switchUpdate();
            }
            return mail;
        } catch (NeedArgumentException | InterruptionFetch e) {
            System.err.println("Ошибка отправки запроса на сервер");
            return null;
        } catch (NotExistingCommandException e) {
            System.err.println("Введенной комманды не существует, введите help для ознакомления со списком комманд");
            return null;
        } catch (IncorrectDataException ignored) {
            System.err.println("Некорректные данные");
            return null;
        }
    }


}
