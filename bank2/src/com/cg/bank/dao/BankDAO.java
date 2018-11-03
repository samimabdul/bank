package com.cg.bank.dao;

import com.cg.bank.dto.Customer;
import com.cg.bank.exception.BankException;

public interface BankDAO {
	
	public Customer createAccount(Customer c);
	public double showBalance (String mobileno);
	public Customer fundTransfer (String sourceMobileNo,String targetMobileNo, double amount) throws BankException;
	public Customer depositAmount (String mobileNo, double amount );
	public Customer withdrawAmount(String mobileNo, double amount) throws BankException;
	public Customer getAccount(String sourceMobileNo);
	public boolean setAccount(String targetMobileNo, double d);

}
