package com.cg.bank.exception;

public class AmountException extends Exception{
	public AmountException() {
			super("Negative amount is not accepted"
					);
	}
}
