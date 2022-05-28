package com.coursework.ServerConnection;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class UserWork extends Authenticator {

        public PasswordAuthentication getPasswordAuthentication () {
            return new PasswordAuthentication("Ayaka", "Seal".toCharArray());
        }

}
