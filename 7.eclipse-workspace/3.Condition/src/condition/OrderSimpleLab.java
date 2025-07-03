package condition;

//import jdk.internal.org.jline.terminal.TerminalBuilder.SystemOutput;

public class OrderSimpleLab {

	public static void main(String[] args) {
		//고객의 주문 정보
		String menuItem = "샌드위치";
		int quantity = 2;
		int customerMoney = 15000;
		int prepTime = 0 ;
		
		
		System.out.println("=== 메가 커피 주문 시스템 ===");
		System.out.println("주문 메뉴 : "+ menuItem);
		System.out.println("주문 수량 : "+ quantity+"개" );
		System.out.println("소지 금액 : "+customerMoney+"원");
		System.out.println();
		
		//메뉴별 가격 설정 (switch문)
		int menuPrice = 0;
		
		switch(menuItem) {
			case "커피":
				menuPrice = 3000;
				System.out.println("☕ 커피 선택 - 가격: "+menuPrice+"원");
				break;
			case "라떼":
				menuPrice = 4000;
				System.out.println("🧋 라떼 선택 - 가격: "+menuPrice+"원");
				break;
			case "샌드위치":
				menuPrice = 5000;
				System.out.println("🥪 샌드위치 선택 - 가격: "+menuPrice+"원");
				break;
			case "케이크":
				menuPrice = 6000;
				System.out.println("🎂 케이크 선택 - 가격: "+menuPrice+"원");
				break;
			default:
				System.out.println("❌ 죄송합니다. 해당 메뉴는 없습니다.");
				System.out.println("메뉴: 커피, 라때, 샌드위치, 케이크");
				return;	
		}
		if(quantity<1) {
			System.out.println("❌ 주문 실패: 수량을 1개 이상 선택해주세요");
			return;
		} else if(quantity>10) {
			System.out.println("❌ 주문 실패: 재고가 부족합니다.(최대 10개)");
			return;
		}
		
		switch(menuItem) {
		case "커피" -> {prepTime = 3;}
		case "라떼" -> {prepTime = 3;}
		case "샌드위치" -> {prepTime = 5;}
		case "케이크" -> {prepTime = 1;}
		}
		
		
		
		
		//총 주문 금액 계산
		int totalPrice = menuPrice*quantity;
		int change = customerMoney-totalPrice;
		int totalPrepTime = prepTime*quantity;
		int needMoney = totalPrice - customerMoney;
		
		if(customerMoney < totalPrice) {
			System.out.println("❌ 주문 실패: 돈이 부족합니다.");
			System.out.println("필요 금액: "+totalPrice+"원");
			System.out.println("부족 금액: "+needMoney+"원");
			return;
		}
		System.out.println("✅ 주문 성공!");
		System.out.println("총 주문 금액: "+totalPrice+"원");
		System.out.println("받은 금액: "+customerMoney+"원");
		System.out.println("거스름돈: "+change+"원");
		System.out.println("예상 준비시간: "+totalPrepTime+"분");
		
		
	}
}
