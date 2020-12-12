package com.cybrilla.splitter.pojo;

import java.util.List;

public class EqualExpense extends Expense{

	public EqualExpense(String name,double amount, User paidBy, List<Split> splits) {
		super(name,amount, paidBy, splits);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean validate() {
		for (Split split : getSplits()) {
			if (!(split instanceof EqualSplit)) {
				return false;
	        }
	    }
	    return true;
	}
	
}
