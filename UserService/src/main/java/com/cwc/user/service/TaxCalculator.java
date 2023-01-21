package com.cwc.user.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class TaxCalculator {

	public static void main(String[] args) {
		boolean flag = true;
		while (flag) {
			Tax tax = new Tax();

			Scanner sc = new Scanner(System.in);
			System.out.println("Enter your name!!! \n");
			String name = sc.nextLine();
			System.out.println("Enter your gender \n");
			Gender genderType = Gender.getByName(sc.next());
			System.out.println("Enter your dob {YYYY-MM-DD } format !!! \n");
			String inputDate = sc.next();
			LocalDate dob = LocalDate.parse(inputDate);

			// display user details
			System.out.println("Personal Details");
			System.out.println("________________________________________________________________");
			System.out.println("Name :- " + name);
			System.out.println("Gender :- " + genderType);
			System.out.println("Birth Date :- " + dob);
			System.out.println("________________________________________________________________");
			System.out.println("Enter Account Details");
			System.out.println("________________________________________________________________");

			System.out.println("Enter your income !!! \n");
			double salary = sc.nextDouble();

			System.out.println("Enter your investment amount!!! \n");
			double investment = sc.nextDouble();

			System.out.println("Enter your House Loan / Rent Amount!!! \n");
			double loanAmount = sc.nextDouble();

			// calling method
			Tax validateUsersTax = tax.validateUsersTax(name, genderType, dob, salary, investment, loanAmount);
		}
		
	}
}
