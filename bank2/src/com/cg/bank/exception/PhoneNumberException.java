package com.cg.bank.exception;

public class PhoneNumberException extends Exception {
	public PhoneNumberException() {
		// TODO Auto-generated constructor stub
		super("Invalid Phone Number\n"
				+ "Mobile Number should contain 10 numeric digits"
			);
	}
}
