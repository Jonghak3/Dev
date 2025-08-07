package clazz;

public class MegacoffeeMain2 {
	
	public static void main(String[] args) {
		MegacoffeeItem[] mega = new MegacoffeeItem[3];
		
		MegacoffeeItem ame = new MegacoffeeItem();
		ame.menu = "아메리카노";
		ame.price = 4500;
		ame.cat ="커피";
		ame.hot = true;
		mega[0] = ame;
		
		MegacoffeeItem cafelatte = new MegacoffeeItem();
		cafelatte.menu = "카페라떼";
		cafelatte.price = 5500;
		cafelatte.cat ="커피";
		cafelatte.hot = true;
		mega[1] = cafelatte;
		
		MegacoffeeItem strade = new MegacoffeeItem();
		strade.menu = "딸기 에이드";
		strade.price = 6000;
		strade.cat ="음료";
		strade.hot = false;
		mega[2] = strade;
		
		System.out.println("=== ☕ 메가 커피 전체 메뉴 ===");
		
		for(MegacoffeeItem item : mega) {
			System.out.println("메뉴: "+item.menu+" | 가격: "+item.price+" | 분류: "+item.cat+" | 온도: "+(item.hot ? "HOT 🔥" : "COLD ❄️"));
		}
		
//		for(MegacoffeeItem item : mega) {
//			if(item.hot = true) {
//				hotcold = "HOT";
//			} else {hotcold = "COLD";}
//			System.out.println("메뉴: "+item.menu+" | 가격: "+item.price+" | 분류: "+item.cat+" | 온도: "+hotcold);
//		}
	}
}
