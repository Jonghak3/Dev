package clazz.productorder.step1.step2.step3;

import java.util.Scanner;

public class ProductOrderMain {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("입력할 주문의 개수를 입력하세요: ");
		int n = scanner.nextInt();
		scanner.nextLine();	//nextInt() 후 남아있는 줄바꿈 문자(\n)를 제거하기 위함.		
		
		//여러 상품의 주문 정보를 담는 배열 생성
		ProductOrder[] orders = new ProductOrder[n];
		for(int i=0; i< orders.length; i++) {
			System.out.println((i+1)+"번째 주문 정보를 입력하세요.");
			System.out.print("상품명 : ");
			String name = scanner.nextLine();
			
			System.out.print("가격 : ");
			int price = scanner.nextInt();
			
			System.out.print("수량 : ");
			int count = scanner.nextInt();
			scanner.nextLine();
			
			orders[i]= createOrder(name, price, count);
		}
		//printOrder()를 사용해서 상품 주문 정보 출력
		printOrder(orders);
		//getTotalAmount()를 사용해서 총 결제 금액 계산
		int totalAmount = getTotalAmount(orders);
		System.out.println("총 결제 금액: "+totalAmount);
		
	}

	public static int getTotalAmount(ProductOrder[] orders) {
		int totalAmount = 0;
		for(ProductOrder order : orders) {
			totalAmount += order.price*order.count;
		} return totalAmount;
		
	}

	public static void printOrder(ProductOrder[] orders) {
		for(ProductOrder order : orders) {
			System.out.println("상품명: "+order.name+", 가격: "+order.price+", 수량: "+order.count);
		}
		
	}

	public static ProductOrder createOrder(String name, int price, int count) {
		ProductOrder order = new ProductOrder();
		order.name = name;
		order.price = price;
		order.count = count;
		return order;
	}
}
