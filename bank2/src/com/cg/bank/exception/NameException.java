package com.cg.bank.exception;

public class NameException extends Exception{
	public NameException() {
		// TODO Auto-generated constructor stub
		super("First letter should be capital remaining should be small letter..., Invalid input :)");
	}
}
