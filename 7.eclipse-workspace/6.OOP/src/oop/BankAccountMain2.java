package oop;


public class BankAccountMain2 {

	public static void main(String[] args) {
		//BankAccountData 객체 생성
		BankAccountData account =new BankAccountData();
		
		//계좌 개설
		System.out.println("계좌를 개설합니다.");
		System.out.println("계좌 번호: "+ account.accountNumber);
		System.out.println("계좌주: "+ account.accountName);
		System.out.println("초기 잔액: "+ account.balance+ "원");
		System.out.println();
		
		//입금
		account.balance += 5000;
		System.out.println("5000원 입금");
		System.out.println("현재 잔액: "+ account.balance+"원");
		System.out.println();
		
		//입금
		account.balance += 3000;
		System.out.println("3000원 입금");
		System.out.println("현재 잔액: "+account.balance+"원");
		System.out.println();
		
		//출금
		account.balance -= 2000;
		System.out.println("2000원 출금");
		System.out.println("현재 잔액: "+ account.balance+"원");
		System.out.println();
		
		//계좌 정보 확인
		System.out.println("계좌 정보를 확인합니다.");
		System.out.println("계좌 번호: "+ account.accountNumber);
		System.out.println("계좌주: "+ account.accountName);
		System.out.println("현재 잔액: "+ account.balance+ "원");
		System.out.println();
		
		//계좌 닫기
		System.out.println("계좌를 닫습니다.");
		System.out.println();
	}
}
