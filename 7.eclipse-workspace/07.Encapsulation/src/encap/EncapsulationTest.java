package encap;

public class EncapsulationTest {

	public static void main(String[] args) {
		//은행 계좌 
		System.out.println("=== 은행 계좌 테스트 ===");
		BankAccount acc1 = new BankAccount("123-456-789", "이순신", 0);
		acc1.deposite(10000);
		acc1.withdraw(3000);
		acc1.withdraw(9000);
		acc1.displyAccountInfo();
		
		//게임 캐릭터
		System.out.println("\n=== 게임 캐릭터 테스트 ===");
		System.out.println("--- 올바른 캐릭터명 테스트 ---");
		GameCharacterV2 char1 = new GameCharacterV2("TestUser01");
		char1.takeDamage(30);
		char1.heal(20);
		char1.levelUp();
		char1.takeDamage(130);
		char1.characterInfo();
	}
}
