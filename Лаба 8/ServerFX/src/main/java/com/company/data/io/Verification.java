package com.company.data.io;

import com.company.utility.Exit;
import com.company.utility.Log;

import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Verification implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Log log = new Log();

    private String LOGIN;
    private String PASS;
    private boolean MODE;

    public static String hashing(String PASS) {
        Log log = new Log();
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            String salt1 = "S3%daO&!2xfeIOJ3N#";
            String salt2 = "D32nQO3B8dbw3x342bewxBBu77eH8";
            byte[] data = (salt1 + PASS + salt2).getBytes(StandardCharsets.UTF_8);
            byte[] hashbytes = sha.digest(data);
            return String.format("%064x", new BigInteger(1, hashbytes));
        } catch (NoSuchAlgorithmException e) {
            log.exception(e);
            System.err.println("Please add SHA-256 algorithm to server");
            throw new Exit();
        }
    }

    public String getLOGIN() {
        return LOGIN;
    }

    public String getPASS() {
        return PASS;
    }

    public boolean isMode() {
        return MODE;
    }

    public void hashing() {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            String salt1 = "S3%daO&!2xfeIOJ3N#";
            String salt2 = "D32nQO3B8dbw3x342bewxBBu77eH8";
            byte[] data = (salt1 + PASS + salt2).getBytes(StandardCharsets.UTF_8);
            byte[] hashbytes = sha.digest(data);
            PASS = String.format("%064x", new BigInteger(1, hashbytes));
        } catch (NoSuchAlgorithmException e) {
            log.exception(e);
            System.err.println("Please add SHA-256 algorithm to server");
            throw new Exit();
        }
    }
}
