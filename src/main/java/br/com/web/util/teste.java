package br.com.web.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class teste {

	public static void main(String[] args) {
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
		System.out.println(enconder.encode("jandeilson"));
	}
}
