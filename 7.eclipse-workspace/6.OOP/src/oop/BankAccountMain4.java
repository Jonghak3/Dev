package oop;


public class BankAccountMain4 {

	public static void main(String[] args) {
		//BankAccountData 객체 생성
		BankAccount account =new BankAccount();
		
		//계좌 개설
		account.open();
		
		//입금
		account.deposite(5000);
		
		//입금
		account.deposite(3000);
		
		//출금
		account.withdraw(2000);
		
		//계좌 정보 확인
		account.showStatus();	
		
		//계좌 닫기
		account.close();
	}	
}
