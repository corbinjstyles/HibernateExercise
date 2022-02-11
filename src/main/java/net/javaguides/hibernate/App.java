package net.javaguides.hibernate;

import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		CRUDOperations crud = new CRUDOperations();
		int c = 1;
		Scanner sc = new Scanner(System.in);
		while(c == 1) {
			
			System.out.println("Please enter the choise by putting the according number with these selections\n"
					+ "1: Insert Student\n"
					+ "2: Find Student by Id\n"
					+ "3: Find all Students in Table\n"
					+ "4: Update email of student by Id\n"
					+ "5: Remove student by Id\n");
			
			int a = sc.nextInt();
			switch(a) {
				case 1:
					crud.insertEntity();
					break;
				case 2:
					crud.findEntity();
					break;
				case 3:
					crud.findEntityAll();
					break;
				case 4:
					crud.updateEntity();
					break;
				case 5:
					crud.removeEntity();
					break;
				default:
					System.out.println("Choose a number between 1 and 5");
					continue;
			}
			System.out.println("Do you want to do another query? 1 for yes or 2 for no");
			sc = new Scanner(System.in);
			c = sc.nextInt();
		}
		sc.close();
		if(c == 2) {
			System.exit(0);
		}
	}
}
