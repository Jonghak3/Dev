package encap;
/*
 * *캡슐화 : 데이터 은닉(private), 메서드 제공(public), 유효성 검증
 * *접근 제한자 이해 - 멤버변수, 메서드
 * 
 * 1) 은행 계좌 관리 시스템
 * 	- 모든 필드는 외부에서 직접 접근 불가능
 * 	- 출금 시에 잔액 부족 검증
 * 	- 입금 시 음수 방지
 * 
 */
public class BankAccount {
	// 계좌번호, 예금주명, 잔액 필드
	private String accountNum;
	private String ownerName;
	private double balance;
	//생성자
	public BankAccount(String accountNum, String ownerName, double balance) {
		//super();
		this.accountNum = accountNum;
		this.ownerName = ownerName;
		this.balance = 0.0;		//초기 잔액 0으로 설정
	}
	
	//입금 메서드 (유효성 검증 포함)
	public void deposite(double amount) {
		if(isAmountValid(amount)) {
			balance += amount;
			System.out.println(amount+"원이 입금되었습니다. 현재 잔액: "+balance+"원");
		} else {
			System.out.println("유효하지 않은 금액입니다. ");
		}
	}
	
	//출금 메서드 (유효성 검증 포함)
	public void withdraw(double amount) {
		if(isAmountValid(amount) && balance - amount >= 0) {
			balance -= amount;
			System.out.println(amount+"원이 출금되었습니다. 현재 잔액: "+balance+"원");
		} else if(balance - amount < 0) {
			System.out.println("잔액이 부족합니다. 현재 잔액: "+ balance+"원");
		} else {
			System.out.println("유효하지 않은 금액입니다. ");
		}
	}
	
	private boolean isAmountValid(double amount) {
		
		return amount > 0;
	}
	
	//잔액 조회 메서드
	public void displayBalance() {
		System.out.println("현재 잔액은 "+balance+"원 입니다");
	}
	//강사님 버전 잔액 조회 메서드 (읽기 전용)
	public double getBalance() {
		return balance;
	}
	
	//계좌 번호 조회 메서드 (읽기 전용)
	public String getAccountNum() {
		return accountNum;
	}	
	
	//계좌 정보 조회 메서드
	public void displyAccountInfo() {
		System.out.println("=== 계좌 정보 ===");
		System.out.println("계좌 번호: "+accountNum);
		System.out.println("예금주: "+ownerName);
		System.out.println("잔액: "+balance);
	}


}
