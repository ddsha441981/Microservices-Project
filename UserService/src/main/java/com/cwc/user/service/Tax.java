package com.cwc.user.service;

import java.time.LocalDate;
import java.time.Period;

import lombok.Builder;

@Builder
public class Tax {

	final double vaildInvestment = 100000;

	public Tax validateUsersTax(String name, Gender genderType, LocalDate dob, double salary, double investment,
			double loanAmount) {
		// calculate date of birth

		 calculateDob(dob);
		
		if (investment <= vaildInvestment) {
			double taxAbleAmount = checkVaildInvestment(investment, loanAmount, salary);
			// print
			System.out.println("Your Taxable Amount is + " + taxAbleAmount);
		} else if (investment >= vaildInvestment) {
			// print
			double taxAbleAmount = checkVaildInvestment(investment, loanAmount, salary);
			System.out.println("Your Taxable Amount is  " + taxAbleAmount);
		}

		return null;

	}

	private double checkVaildInvestment(double investment, double loanAmount, double salary) {
		if (investment != 0) {
			double afterDecrese = salary - loanAmount;
			double remainingAmount = loanAmount * 80 / 100;
			double re = loanAmount - remainingAmount;
//			double result = investment - vaildInvestment;
			salary = afterDecrese + re;
			double mainAmount = salary - vaildInvestment;
			return mainAmount;
		} else {
			double afterDecrese = salary - loanAmount;
			double remainingAmount = loanAmount * 80 / 100;
			double remainingSalary = loanAmount - remainingAmount;
//			double result = investment - vaildInvestment;
			salary = afterDecrese + remainingSalary;
//			double mainAmount = salary - vaildInvestment; 
			return salary;
		}

	}

	private static int calculateDob(LocalDate dob) {

		LocalDate currentDate = LocalDate.now();
		if ((dob != null) && (currentDate != null)) {
			return Period.between(dob, currentDate).getYears();
		} else {
			return 0;
		}

	}
}

//
//if((genderType.equals(Gender.MALE)) && (calculatedDob<=60) && (salary<=160000.00)) {
//	System.out.println("No tax  for male");
//}else if((genderType.equals(Gender.FEMALE)) && (calculatedDob <= 60) && (salary<=190000.00)) {
//	System.out.println("No Tax for female ");
//}else if((genderType.equals(Gender.MALE)) && (calculatedDob >= 60) && (salary<=240000.00)) {
//	System.out.println("No Tax For Male Senior Cityzen");
//}else if((genderType.equals(Gender.FEMALE)) && (calculatedDob >= 60) && (salary<=240000.00)) {
//	System.out.println("No Tax For Female Senior Cityzen");
//}