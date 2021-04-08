package com.kim.advertise;

import java.util.Scanner;

public class demo2e {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		int amount = input.nextInt();

		if (amount > 5)
			System.out.println("greater than 5");
		else
			System.out.println("less than 5");
		
	}
}
