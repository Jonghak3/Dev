package clazz.productorder.step1;

public class ProductOrderMain2 {

	public static void main(String[] args) {
		ProductOrder[] prord =new ProductOrder[3];
		
		ProductOrder dubu = new ProductOrder();
		dubu.name = "두부";
		dubu.price = 2000;
		dubu.count = 2;
		prord[0] = dubu;
		
		ProductOrder kimchi = new ProductOrder();
		kimchi.name = "김치";
		kimchi.price = 5000;
		kimchi.count = 1;
		prord[1] = kimchi;
		
		ProductOrder coke = new ProductOrder();
		coke.name = "콜라";
		coke.price = 1500;
		coke.count = 2;
		prord[2] = coke;
		int totalPrice = 0;
		for(ProductOrder order : prord) {
			System.out.println("상품명: "+order.name+", 가격: "+order.price+", 수량: "+order.count);
			totalPrice += order.price*order.count;
		}
		System.out.println("총 결제 금액: "+totalPrice);
	}
}
