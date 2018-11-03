package com.cg.bank.service;

import com.cg.bank.dto.Customer;
import com.cg.bank.exception.BankException;
import com.cg.bank.exception.AmountException;
import com.cg.bank.exception.NameException;
import com.cg.bank.exception.PhoneNumberException;

public interface BankService {
	
	public Customer createAccount(Customer c);
	public double showBalance (String mobileno) throws PhoneNumberException, BankException;
	public Customer fundTransfer (String sourceMobileNo,String targetMobileNo, double amount) throws BankException;
	public Customer depositAmount (String mobileNo, double amount ) throws PhoneNumberException, AmountException, BankException;
	public Customer withdrawAmount(String mobileNo, double amount) throws BankException;
	public boolean validateUserName(String name) throws NameException;
	public boolean validatePhoneNumber(String mobNo) throws PhoneNumberException;
	public boolean validateTargetMobNo(String targetMobNo) throws PhoneNumberException;
	public boolean validateAmount(double amt) throws AmountException;
	public boolean validateAll(Customer c) throws BankException, NameException, AmountException, PhoneNumberException;
	
}
