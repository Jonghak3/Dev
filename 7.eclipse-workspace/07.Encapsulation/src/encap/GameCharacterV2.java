package encap;

import java.util.Objects;

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
public class GameCharacterV2 {

	// private 필드 선언
	private String characterNmae;
	private int level;
	private int hp;
	private int maxHp;
	//생성자
	public GameCharacterV2(String characterNmae) {
		//super();
		this.characterNmae = Objects.requireNonNull(characterNmae, "캐릭터명은 null일 수 없습니다.") ;
		this.maxHp = 100;	//초기 최대 체력
		this.hp = maxHp;	//현재 체력을 최대 체력으로 설정
		this.level = 1;
		
		System.out.println("캐릭터 '"+characterNmae+"'이(가) 생성되었습니다.");
	}
	
	//체력 회복 메서드 : 체력 증가 처리 
	public void heal(int amount) {
		if(amount <= 0) {
			System.out.println("회복량은 0보다 커야 합니다.");
			return;
		}
		
		int oldHp = hp;	//기존 체력 저
		hp += amount;	//체력 증가
		
		//최대 체력 초과 방지
		if(hp > maxHp) {
			hp =maxHp;
		}
		
		System.out.println(characterNmae+"이(가) "+(hp-oldHp)+"만큼 회복했습니다.");
		System.out.println("현재 체력 : "+ hp+"/"+maxHp);
	}
	
	//데미지 받기 메서드 : 체력 감소 처리
	public void takeDamage(int amount) {
		if(amount <= 0) {
			System.out.println("데미지는 0보다 커야 합니다.");
			return;
		}
		
		hp -= amount;		//체력 감소
		
		//체력은 0 이하로 떨어지지 않도록 함
		if(hp < 0) {
			hp = 0;
		}
		
		System.out.println(characterNmae+"이(가) "+amount+"의 데미지를 받았습니다.");
		System.out.println("현재 체력 : "+ hp+"/"+maxHp);
		
		// 사망 확인
		if(hp == 0) {
			System.out.println(characterNmae+"이(가) 사망했습니다.");
		}
	}
	//레벨업 메서드 : 레벨 증가 최대 체력 증가
	public void levelUp() {
		level++;
		int hpIncrease = 20;	//레벨업 시 최대 체력 증가분
		maxHp += hpIncrease;	//최대 체력 증가
		hp = maxHp;
		System.out.println(characterNmae+"이(가) 레벨업했습니다!");
		System.out.println("현재 레벨: "+level);
		System.out.println("최대 체력이 "+ hpIncrease +" 증가했습니다!");
		System.out.println("체력이 완전히 회복되었습니다. ("+hp+"/"+maxHp+")");
	}
	
	
	
	//사망 여부 확인 메서드
	public boolean isDead() {
		return hp <= 0;		//체력이 0 이하면 사망
	}

	public String getCharacterNmae() {
		return characterNmae;
	}
	
	//캐릭터 정보 조회 메서드
	public void characterInfo() {
		System.out.println("=== 캐릭터 정보 ===");
		System.out.println("이름: "+characterNmae);
		System.out.println("레벨: "+level);
		System.out.println("체력 "+hp+"/"+maxHp);
		System.out.println("상태: "+(isDead() ? "사망": "생존"));
	}
	
}
