package com.cybrilla.splitter.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cybrilla.splitter.pojo.Expense;
import com.cybrilla.splitter.pojo.ExpenseType;
import com.cybrilla.splitter.pojo.Split;
import com.cybrilla.splitter.pojo.User;

public class ExpenseManager {
	List<Expense> expenses;
	public Map<String, User> userMap;
	Map<String, Map<String, Double>> balanceSheet;

	public ExpenseManager() {
		expenses = new ArrayList<Expense>();
		userMap = new HashMap<String, User>();
		balanceSheet = new HashMap<String, Map<String, Double>>();
	}

	public void addUser(User user) {
		userMap.put(user.getName(), user);
		balanceSheet.put(user.getName(), new HashMap<String, Double>());
	}

	public void addExpense(ExpenseType expenseType,String name, double amount, String paidBy, List<Split> splits) {
		Expense expense = ExpenseService.createExpense(expenseType, name, amount, userMap.get(paidBy), splits);
		expenses.add(expense);
		for (Split split : expense.getSplits()) {
			String paidTo = split.getUser().getName();
			Map<String, Double> balances = balanceSheet.get(paidBy);
			if (!balances.containsKey(paidTo)) {
				balances.put(paidTo, 0.0);
			}
			balances.put(paidTo, balances.get(paidTo) + split.getAmount());
			balances = balanceSheet.get(paidTo);
			if (!balances.containsKey(paidBy)) {
				balances.put(paidBy, 0.0);
			}
			balances.put(paidBy, balances.get(paidBy) - split.getAmount());
		}
	}

	public void showBalance(String userId) {
		boolean isEmpty = true;
		for (Map.Entry<String, Double> userBalance : balanceSheet.get(userId).entrySet()) {
			if (userBalance.getValue() != 0) {
				isEmpty = false;
				printBalance(userId, userBalance.getKey(), userBalance.getValue());
			}
		}
		if (isEmpty) {
			System.out.println("No balances");
		}
	}

	public void showBalances() {
		boolean isEmpty = true;
		for (Map.Entry<String, Map<String, Double>> allBalances : balanceSheet.entrySet()) {
			for (Map.Entry<String, Double> userBalance : allBalances.getValue().entrySet()) {
				if (userBalance.getValue() > 0) {
					isEmpty = false;
					printBalance(allBalances.getKey(), userBalance.getKey(), userBalance.getValue());
				}
			}
		}
		if (isEmpty) {
			System.out.println("No balances");
		}
	}

	private void printBalance(String user1, String user2, double amount) {
		String user1Name = userMap.get(user1).getName();
		String user2Name = userMap.get(user2).getName();
		if (amount < 0) {
			System.out.println(user1Name + " owes " + Math.abs(amount)+" to "+ user2Name);
		} else if (amount > 0) {
			System.out.println(user2Name + " owes " + Math.abs(amount)+" to "+ user1Name);
		}
	}
}
