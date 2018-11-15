package com.javastudio.lms.tutorial.web.security;

import org.apache.shiro.authc.credential.PasswordService;
import org.mindrot.jbcrypt.BCrypt;

import java.io.Serializable;

public class BCryptPasswordService implements PasswordService, Serializable {

    private static final long serialVersionUID = -4061983594644196787L;

    public static final int DEFAULT_BCRYPT_ROUND = 10;

    private int logRounds;

    public BCryptPasswordService() {
        this.logRounds = DEFAULT_BCRYPT_ROUND;
    }

    public BCryptPasswordService(int logRounds) {
        this.logRounds = logRounds;
    }

    @Override
    public String encryptPassword(Object plaintextPassword) {
        if (plaintextPassword instanceof String) {
            String password = (String) plaintextPassword;
            return BCrypt.hashpw(password, BCrypt.gensalt(logRounds));
        }
        throw new IllegalArgumentException(
                "BCryptPasswordService encryptPassword only support java.lang.String credential.");
    }

    @Override
    public boolean passwordsMatch(Object submittedPlaintext, String encrypted) {
        if (submittedPlaintext instanceof char[]) {
            String password = String.valueOf((char[]) submittedPlaintext);
            return BCrypt.checkpw(password, encrypted);
        }
        throw new IllegalArgumentException(
                "BCryptPasswordService passwordsMatch only support char[] credential.");
    }

    public void setLogRounds(int logRounds) {
        this.logRounds = logRounds;
    }

    public int getLogRounds() {
        return logRounds;
    }
}
