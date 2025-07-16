package encap;

/*
 * * 요구사항
 * 	- 캐릭터명, 레벨, 체력, 최대체력 필드
 * 	- 체력 회복, 데미지 받기 메서드 제공
 * 	- 레벨업 메서드 
 * 	- 체력은 0 이하로 떨어지지 않고, 최대체력을 초과하지 않음
 * 	- 캐릭터 사망 여부 확인 메서드 제공
 * 
 * 
 */
public class GameCharacter {

	// private 필드 선언
	private String characterNmae;
	private int level;
	private int hp;
	private int maxHp;
	private boolean isAlive;
	//생성자
	public GameCharacter(String characterNmae) {
		//super();
		this.characterNmae = characterNmae;
		this.maxHp = 100;
		this.hp = 100;
		this.level = 1;
		this.isAlive = true;
		
		System.out.println("캐릭터 '"+characterNmae+"'이(가) 생성되었습니다.");
	}
	
	//체력 회복 메서드
	public void recoverHp(int recoverHp) {
		if(hp+recoverHp <= maxHp) {
			hp += recoverHp;
			System.out.println(characterNmae+"이(가) "+recoverHp+"만큼 회복했습니다.");
			System.out.println("현재 체력: "+hp+"/"+maxHp);
		} else if(hp+recoverHp > maxHp) {
			System.out.println(characterNmae+"이(가) "+(maxHp-hp)+"만큼 회복했습니다.");
			hp = maxHp;
			System.out.println("현재 체력: "+hp+"/"+maxHp);
		}
	}
	
	//데미지 받기 메서드
	public void takeDamage(int damage) {
		if(damage>=hp) {
			System.out.println(characterNmae+"이(가) "+damage+"의 데미지를 받았습니다.");
			hp=0;
			System.out.println("현재 체력: "+hp+"/"+maxHp);
			System.out.println("캐릭터가 사망하였습니다.");
			isAlive = false;
		} else {
			System.out.println(characterNmae+"이(가) "+damage+"의 데미지를 받았습니다.");
			hp -= damage;
			System.out.println("현재 체력: "+hp+"/"+maxHp);
		}
	}
	//레벨업 메서드
	public void levelUp() {
		level++;
		maxHp += 20;
		hp = maxHp;
		System.out.println(characterNmae+"이(가) 레벨업했습니다!");
		System.out.println("현재 레벨: "+level);
		System.out.println("최대 체력이 20 증가했습니다!");
		System.out.println("체력이 완전히 회복되었습니다. ("+hp+"/"+maxHp+")");
	}
	
	//캐릭터 정보 조회 메서드
	public void characterIfo() {
		System.out.println("=== 캐릭터 정보 ===");
		System.out.println("이름: "+characterNmae);
		System.out.println("레벨: "+level);
		System.out.println("체력 "+hp+"/"+maxHp);
		System.out.println("상태: "+(isAlive ? "생존": "사망"));
	}
	
	//사망 여부 확인 메서드
}
