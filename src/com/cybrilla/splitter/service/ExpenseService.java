package com.cybrilla.splitter.service;

import java.util.List;

import com.cybrilla.splitter.pojo.EqualExpense;
import com.cybrilla.splitter.pojo.Expense;
import com.cybrilla.splitter.pojo.ExpenseType;
import com.cybrilla.splitter.pojo.Split;
import com.cybrilla.splitter.pojo.User;

public class ExpenseService {
	public static Expense createExpense(ExpenseType expenseType, String name, double amount, User paidBy,
			List<Split> splits) {
		switch (expenseType) {
		case EQUAL:
			int totalSplits = splits.size();
			double splitAmount = ((double) Math.round(amount * 100 / totalSplits)) / 100.0;
			for (Split split : splits) {
				split.setAmount(splitAmount);
			}
			splits.get(0).setAmount(splitAmount + (amount - splitAmount * totalSplits));
			return new EqualExpense(name, amount, paidBy, splits);
		default:
			return null;
		}
	}
}
