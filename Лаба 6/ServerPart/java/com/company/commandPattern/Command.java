package com.company.commandPattern;

import com.company.data.io.Answer;
import com.company.data.io.Mail;

/**
 * This interface implements all Commands
 */
public interface Command {
    Answer execute(Mail command);

    String help();
}

