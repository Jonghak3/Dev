package constructor4;

public class FlexibleAccount {
	String accountNumber;
	String ownerName;
	long balance;
	
	//기본 생성자를 명시적으로 정의
	public FlexibleAccount() {
		System.out.println("기본 생성자 호출: 빈 계좌 생성");
		this.accountNumber = "미설정";
		this.ownerName = "미설정";
		this.balance = 0;
	}

	public FlexibleAccount(String accountNumber, String ownerName, long balance) {
		//super();
		System.out.println("매개변수 생성자 호출:   완전한 계좌 생성");
		this.accountNumber = accountNumber;
		this.ownerName = ownerName;
		this.balance = balance;
	}
	
	void printInfo() {
		System.out.println("계좌 번호: "+accountNumber+
							", 예금주 : "+ownerName+
							", 잔액 : "+balance);
	}
	
	void setAccountInfo(String accountNumber, String ownerName, long balance) {
		this.accountNumber = accountNumber;
		this.ownerName = ownerName;
		this.balance = balance;
	}
	
	
}
