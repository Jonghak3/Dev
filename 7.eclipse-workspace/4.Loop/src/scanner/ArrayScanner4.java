package scanner;

import java.util.Scanner;

/*
       - 예) 입력받을 숫자의 개수를 입력하세요: 3
       		 1
             2
             3
            입력한 정수의 합계: 6
            입력한 정수의 평균: 2.0   
 */
public class ArrayScanner4 {

	public static void main(String[] args) {
		// 콘솔에서 사용자 입력 받기 위한 Scanner 객체 생성
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("입력받을 숫자의 개수를 입력하세요: ");
		//배열의 크기를 동적으로 할당받을 변수
		int count = scanner.nextInt();
		
		//사용자가 입력한 개수만큼 배열 동적 할당
		int[] numbers = new int[count];	
		
		//합계 저장할 변수 
		int sum = 0;
		//평균 저장할 변수
		double average;
		
		//for문 사용해서 배열의 각 인덱스에 사용자 입력한 값 저장
		for(int i = 0; i < count; i++) {
			numbers[i] = scanner.nextInt();	// 정수 입력받아 배열에 저장
			sum += numbers[i];  //입력받은 값을 합계에 누적 
		}	
		// 평균
		average = (double)sum / count;
		
		// 출력
		System.out.println("입력한 정수의 합계: " + sum);
		System.out.println("입력한 정수의 평균: " + average);
		
		scanner.close();
	}
}





















