package com.cg.bank.dao;

import java.sql.Connection;
import java.util.Map;
import java.util.Set;

import com.cg.bank.dto.Customer;
import com.cg.bank.exception.BankException;

public class BankDAOImpl implements BankDAO {
	
	Map<String, Customer> map;
//	public Connection con;
	
	public BankDAOImpl() {
		map = DataContainer.createCollection();
//		con = DBConnection.getConnection();
	}
	
//	public Connection con;
//	
//	public BankDAOImpl() {
//		con = DBConnection.getConnection();
//	}

	@Override
	public Customer createAccount(Customer c) {
		// TODO Auto-generated method stub
		map.put(c.getMobileNumber(), c);
		return c;
	}

	@Override
	public double showBalance(String mobileno) {
		// TODO Auto-generated method stub
		Customer cShow = map.get(mobileno);
		double bal = cShow.getAmount();
		return bal;
	}

	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, double amount) throws BankException {
		// TODO Auto-generated method stub
		Customer cfunds = map.get(sourceMobileNo);
		Customer cfundr = map.get(targetMobileNo);
		if((cfunds.getAmount()-amount) >= 500){
			cfundr.setAmount(cfundr.getAmount()+amount);
			map.put(targetMobileNo, cfundr);
			cfunds.setAmount(cfunds.getAmount()-amount);
			map.put(sourceMobileNo, cfunds);
			
//			Set<String> set = map.keySet();
//			for(Set<String> c : set){
//				if(c.con)
//			}
			
		}
		else
			throw new BankException("Make sure that overall balance is greater than 500...");
		
//		cfunds = new Customer();
		return cfunds;
	}

	@Override
	public Customer depositAmount(String mobileNo, double amount) {
		// TODO Auto-generated method stub
		Customer c = map.get(mobileNo);
		c.setAmount(c.getAmount()+amount);
		map.put(mobileNo, c);
		
		return c;
	}

	@Override
	public Customer withdrawAmount(String mobileNo, double amount) throws BankException {
		// TODO Auto-generated method stub
		Customer c = map.get(mobileNo);
		if((c.getAmount()-amount) >= 500){
			c.setAmount(c.getAmount()-amount);
			map.put(mobileNo, c);
		}
		else
			throw new BankException("check In show balance option to know that if your balance is gater than 500");
		return c;
	}

	@Override
	public Customer getAccount(String sourceMobileNo) {
		// TODO Auto-generated method stub
		return map.get(sourceMobileNo);
	}

	@Override
	public boolean setAccount(String targetMobileNo, double d) {
		// TODO Auto-generated method stub
		Customer c = map.get(targetMobileNo);
		c.setAmount(d);
		map.put(targetMobileNo, c);
		return true;
	}

	

}
