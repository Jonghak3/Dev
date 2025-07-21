package codingtest;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int h, m, x, y;
		h = sc.nextInt();
		m = sc.nextInt();
		x = sc.nextInt();
		y = h*60+m+x;
		
		if( y >= 60*24) {
			h = (y - 60*24)/60;
			m = (y-60*24)%60;
		} else if (y < 60*24) {
			h = h+(m+x)/60;
		} 

		sc.close();
		
	}
}
