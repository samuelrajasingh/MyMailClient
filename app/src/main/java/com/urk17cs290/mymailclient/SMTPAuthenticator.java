package com.urk17cs290.mymailclient;


import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {
	public SMTPAuthenticator() {

		super();
	}

	@Override
	public PasswordAuthentication getPasswordAuthentication() {
		String username = "rajasinghsamuelb@karunya.edu.in";
		String password = "ctgpbbqrhthgyayg";
		if ((username != null) && (username.length() > 0) && (password != null)
					&& (password.length() > 0)) {

			return new PasswordAuthentication(username, password);
		}

		return null;
	}
}