package com.cg.bank.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.cg.bank.exception.AmountException;
import com.cg.bank.exception.NameException;
import com.cg.bank.exception.PhoneNumberException;
import com.cg.bank.service.BankServiceImpl;

public class BankTest {
	
	@Test
	public void ValidateNameTrue() throws NameException{
		BankServiceImpl bs = new BankServiceImpl();
		assertEquals(true, bs.validateUserName("Samim"));
	}
	@Test 
	public void ValidateName() throws NameException{
		BankServiceImpl bs = new BankServiceImpl();
		assertEquals(false, bs.validateUserName("Samim523"));
		assertEquals(false, bs.validateUserName("Samim*9556"));
		assertEquals(false, bs.validateUserName("85621"));
		assertEquals(false, bs.validateUserName("samim"));
	}
	
//	@Test (expected = InvalidNameFormat.class)
//	public void ValidateUserNameV1(){
//		BankServiceImpl bs = new BankServiceImpl();
		
//	}
	
	@Test
	public void ValidatePhonNumberTrue() throws PhoneNumberException{
		BankServiceImpl bs = new BankServiceImpl();
		assertEquals(true, bs.validatePhoneNumber("9674909679"));
	}
	
	@Test
	public void ValidatePhoneNumber() throws PhoneNumberException{
		BankServiceImpl bs = new BankServiceImpl();
		assertEquals(false, bs.validatePhoneNumber("984645632"));
		assertEquals(false, bs.validatePhoneNumber("4634862470"));
		assertEquals(false, bs.validatePhoneNumber("596317"));
		assertEquals(false, bs.validatePhoneNumber("testing"));
		assertEquals(false, bs.validatePhoneNumber("@#*%"));
	}
	

	
	@Test
	public void ValidateAmountTrue() throws AmountException{
		BankServiceImpl bs = new BankServiceImpl();
		assertEquals(true, bs.validateAmount(150));
	}
	
	@Test 
	public void ValidateAmount() throws AmountException{
		BankServiceImpl bs = new BankServiceImpl();
		assertEquals(false, bs.validateAmount(0));
		assertEquals(false, bs.validateAmount(-100));
	}

}
