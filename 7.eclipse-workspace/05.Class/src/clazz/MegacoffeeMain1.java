package clazz;

public class MegacoffeeMain1 {
	
	public static void main(String[] args) {
		MegacoffeeItem[] mega = new MegacoffeeItem[2];
		
		MegacoffeeItem ame = new MegacoffeeItem();
		ame.menu = "아메리카노";
		ame.price = 4500;
		ame.cat ="커피";
		ame.hot = true;
		mega[0] = ame;
		
		MegacoffeeItem cake = new MegacoffeeItem();
		cake.menu = "뉴욕 치즈케이크";
		cake.price = 6800;
		cake.cat ="디저트";
		cake.hot = false;
		mega[1] = cake;
		
		System.out.println("=== 메가 커피 인기 메뉴 ===");
		String hotcold;
		
		for(MegacoffeeItem item : mega) {
			System.out.println("메뉴: "+item.menu+" | 가격: "+item.price+" | 분류: "+item.cat+" | 온도: "+(item.hot ? "HOT" : "COLD"));
		}
		
//		for(MegacoffeeItem item : mega) {
//			if(item.hot = true) {
//				hotcold = "HOT";
//			} else {hotcold = "COLD";}
//			System.out.println("메뉴: "+item.menu+" | 가격: "+item.price+" | 분류: "+item.cat+" | 온도: "+hotcold);
//		}
	}
}
