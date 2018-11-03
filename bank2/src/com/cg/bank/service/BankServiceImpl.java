package com.cg.bank.service;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.bank.dao.BankDAO;
import com.cg.bank.dao.BankDAOImpl;
import com.cg.bank.dto.Customer;
import com.cg.bank.exception.BankException;
import com.cg.bank.exception.AmountException;
import com.cg.bank.exception.PhoneNumberException;
import com.cg.bank.exception.NameException;

public class BankServiceImpl implements BankService {

    BankDAO dao;

    public BankServiceImpl() {
        dao = new BankDAOImpl();
    }

    @Override
    public boolean validateAll(Customer c) throws BankException, NameException, AmountException, PhoneNumberException {

        boolean b = false;

        if (validateUserName(c.getCustomerName()) == true && validatePhoneNumber(c.getMobileNumber()) == true && validateAmount(c.getAmount()) == true)
            b = true;
        if (!b)
            throw new BankException("Invalid details");
        return b;
    }

    @Override
    public boolean validateUserName(String name) throws NameException {

        Pattern p = Pattern.compile("[A-Z]{1}[a-z]{2,30}");
        Matcher mat = p.matcher(name);
        boolean b = mat.matches();

        if (!b)
            throw new NameException();

        return b;
    }

    @Override
    public boolean validatePhoneNumber(String mobileNo) throws PhoneNumberException {

        Pattern pat = Pattern.compile("[6-9]{1}[0-9]{9}");
        Matcher mat = pat.matcher(mobileNo);
        boolean b = mat.matches();

        if (!b)
            throw new PhoneNumberException();

        return b;
    }

    @Override
    public boolean validateAmount(double amt) throws AmountException {

        Pattern pat = Pattern.compile("[1-9]{1}[0-9.]{0,9}");
        Matcher mat = pat.matcher(String.valueOf(amt));
        boolean b = mat.matches();

        if (!b)
            throw new AmountException();

        return b;
    }

    @Override
    public boolean validateTargetMobNo(String targetMobNo) throws PhoneNumberException {

        Pattern pt = Pattern.compile("[6-9]{1}[0-9]{9}");
        Matcher m = pt.matcher(targetMobNo);
        boolean b = m.matches();

        if (!b)
            throw new PhoneNumberException();

        return b;
    }

    @Override
    public Customer createAccount(Customer c) {
        // TODO Auto-generated method stub
        return dao.createAccount(c);
    }

    @Override
    public double showBalance(String mobileno) throws PhoneNumberException, BankException {
        // TODO Auto-generated method stub
        Customer b = dao.getAccount(mobileno);
        if (b == null)
            throw new BankException("Mobile number doesn't exist");
        return b.getAmount();
    }

    @Override
    public Customer fundTransfer(String sourceMobileNo, String targetMobileNo,
            double amount) throws BankException {
        // TODO Auto-generated method stub
        Customer s = dao.getAccount(sourceMobileNo);
        Customer t = dao.getAccount(targetMobileNo);
        if (s == null)
            throw new BankException("Mobile number doesn't exist");
        if (t == null)
            throw new BankException("Mobile number doesn't exist");
        double ts = (s.getAmount() - amount);
        if (ts >= 0) {
            dao.setAccount(targetMobileNo, t.getAmount() + amount);
            dao.setAccount(sourceMobileNo, s.getAmount() - amount);
        } else {
            throw new BankException(
                    "Minimum balance of Rupees greater than zero should be available...");
        }
        return dao.getAccount(sourceMobileNo);
    }

    @Override
    public Customer depositAmount(String mobileNo, double amount) throws PhoneNumberException, AmountException, BankException {
        // TODO Auto-generated method stub
        Customer c1 = dao.getAccount(mobileNo);
        if (c1 == null)
            throw new BankException("Mobile number doesn't exist");
        boolean c = dao.setAccount(mobileNo, c1.getAmount() + amount);
        Customer cDep = dao.getAccount(mobileNo);
        if (!c)
            throw new BankException("Unable to deposit");
        else
            return cDep;
    }

    @Override
    public Customer withdrawAmount(String mobileNo, double amount)
            throws BankException {
        // TODO Auto-generated method stub
        boolean c = false;
        Customer cD = dao.getAccount(mobileNo);
        if (cD == null)
            throw new BankException("Mobile number doesn't exist");
        if ((cD.getAmount() - amount) >= 0)
            c = dao.setAccount(mobileNo, cD.getAmount() - amount);
        Customer cE = dao.getAccount(mobileNo);
        if (!c)
            throw new BankException("Unable to withdraw");
        else
            return cE;
    }

    
}
