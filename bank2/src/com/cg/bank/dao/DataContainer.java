package com.cg.bank.dao;

import java.util.HashMap;
import java.util.Map;

import com.cg.bank.dto.Customer;

public class DataContainer {

	private static Map<String, Customer> maps;

	public static Map<String, Customer> createCollection() {

		if (maps == null)
			maps = new HashMap<String, Customer>();

		return maps;
	}

}
