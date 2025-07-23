package codingtest;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int totalPrice, itemCount;
		totalPrice = sc.nextInt();
		itemCount = sc.nextInt();
		int a,b;
		int sum = 0;
		for(int i =1 ; i <= itemCount; i++) {
			a = sc.nextInt();
			b = sc.nextInt();
			sum += a*b;
		}
		System.out.println((totalPrice==sum)?"Yes" : "No");
		
		sc.close();
		
	}
}
