package encap;

public class GameCharacterMainV2 {

	public static void main(String[] args) {
		System.out.println("=== 게임 캐릭터 테스트 ===");
		System.out.println("--- 올바른 캐릭터명 테스트 ---");
		GameCharacterV2 char1 = new GameCharacterV2("TestUser01");
		char1.takeDamage(30);
		char1.heal(20);
		char1.levelUp();
		char1.takeDamage(130);
		char1.characterInfo();
	}
}
