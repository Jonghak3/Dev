package method;

import java.util.Scanner;

public class MethodTraining {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		//프로그램 시작 메시지
		printHeader();
		
		//메뉴 선택(반복문)
		while(true) {
			//문제 목록 출력
			problemList();
			
			System.out.print("풀어볼 문제 번호를 선택하세요 (0: 종료): ");
			int choice = scanner.nextInt();
			
			//종료 조건
			if(choice == 0) {
				System.out.println("수고하셨습니다! 취업 성공하세요!!");
				break;
			} else if(choice == 1) {
				//선택한 문제 실행
				solveProblem(choice, scanner);
			} else if(choice == 2) {
				solveProblem(choice, scanner);
			}
			

		}
		
		scanner.close();
	}
	
	public static void solveProblem(int choiceNumber, Scanner scanner) {
		switch(choiceNumber) {
		case 1:
			solveFindMax(scanner);
			break;
		case 2:
			solveFindPrime(scanner);
			break;
		default:
			System.out.println("잘못된 번호입니다.");
		}	
		
	}

	public static void solveFindPrime(Scanner scanner) {
		System.out.println("\n=== 문제 2: 소수 판별하기 === ");
		System.out.println("숫자를 입력하면 소수인지 판별해드립니다.");
		System.out.print("숫자를 입력하세요: ");
		int number = scanner.nextInt();	//입력받을 숫자의 개수
		int divisorCount = 0;
		for(int i = number; i >= 2; i--) {
			if(number%i == 0) {
				divisorCount ++;
			}
		}
		if(divisorCount == 1) {
			System.out.println(number+"은(는) 소수입니다.");
		} else {
			System.out.println(number+"은(는) 소수가 아닙니다.");
		}
		printSeparator();
		
	}

	public static void solveFindMax(Scanner scanner) {
		System.out.println("\n=== 문제 1: 최댓값 찾기 === ");
		System.out.println("여러 개의 숫자를 입력하여 최댓값을 찾아보세요.");
		System.out.print("숫자 개수를 입력하세요: ");
		int count = scanner.nextInt();	//입력받을 숫자의 개수
		//최대값을 저장할 변수(가장 작은 값으로 초기화)
		int max = Integer.MIN_VALUE;
		//반복문
		for(int i = 1; i <= count; i++) {
			System.out.print(i+"번째 숫자: ");
			int number = scanner.nextInt();
			//최대값 업데이트하는 메서드 호출
			max = findMax(max, number);
		}
		System.out.println("최댓값: "+max);
		printSeparator();
	}

	public static void printSeparator() {
		System.out.println("----------------------------------");
		
	}

	public static int findMax(int max, int number) {
		if(max > number)
			return max;
		else
			return number;
	}

	public static void problemList() {
		System.out.println("\n===문제 목록===");
		System.out.println("1. 최대값 찾기(배열 없이)");
		System.out.println("2. 소수 판별하기");
		System.out.println("0. 프로그램 종료");
		System.out.println();
		
	}

	/*
	 * 프로그램 헤더 
	 */
	public static void printHeader() {
		System.out.println("==========================");
		System.out.println("      메서드 훈련");
		System.out.println("      면접 대비");
		System.out.println("==========================");
	}
}
