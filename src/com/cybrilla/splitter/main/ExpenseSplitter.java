package com.cybrilla.splitter.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.cybrilla.splitter.pojo.EqualSplit;
import com.cybrilla.splitter.pojo.ExpenseType;
import com.cybrilla.splitter.pojo.Split;
import com.cybrilla.splitter.pojo.User;
import com.cybrilla.splitter.service.ExpenseManager;

public class ExpenseSplitter {
	public static void main(String[] args) {
		ExpenseManager expenseManager = new ExpenseManager();
		expenseManager.addUser(new User("Ajay", "ajay@gmail.com"));
		expenseManager.addUser(new User("Ram", "ram@gmail.com"));
		expenseManager.addUser(new User("Krish", "krish@gmail.com"));
		expenseManager.addUser(new User("Kumar", "kumar@gmail.com"));
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Enter the Option from - ");
			System.out.println("SHOW");
			System.out.println("SHOW User Name from - "+expenseManager.userMap);
			System.out.println("EXPENSE");
			String command = scanner.nextLine();
			String[] commands = command.split(" ");
			String commandType = commands[0];
			switch (commandType) {
			case "SHOW":
				if (commands.length == 1) {
					expenseManager.showBalances();
				} else {
					expenseManager.showBalance(commands[1]);
				}
				break;
			case "EXPENSE":
				System.out.println("Enter Details of Expense differentiate by space - ");
				String[] values = scanner.nextLine().split(" ");
				String paidBy = values[2];
				Double amount = Double.parseDouble(values[1]);
				String name = values[0];
				String expenseType = values[3];
				List<Split> splits = new ArrayList<>();
				switch (expenseType) {
				case "EQUAL":
					for (Map.Entry<String,User> entry : expenseManager.userMap.entrySet()) {
						if(entry.getKey().equalsIgnoreCase(paidBy)) {
							
						}else {
							splits.add(new EqualSplit(entry.getValue()));
						}
					}
					expenseManager.addExpense(ExpenseType.EQUAL, name, amount, paidBy, splits);
					break;
				}
				break;
			}
		}
	}
}
