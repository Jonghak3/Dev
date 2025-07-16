package encap;

public class GameCharacterMain {

	public static void main(String[] args) {
		System.out.println("=== 게임 캐릭터 테스트 ===");
		System.out.println("--- 올바른 캐릭터명 테스트 ---");
		GameCharacter char1 = new GameCharacter("TestUser01");
		char1.takeDamage(30);
		char1.recoverHp(20);
		char1.levelUp();
		char1.takeDamage(130);
		char1.characterIfo();
	}
}
