package extend6.teachersVersion;

/*
 *  * 멤버십 프로그램을 구현하시오.
 *  	- 회사에서 고객 정보를 활용한 맞춤형 서비스를 제공하고자 합니다.
 *  	- 일반고객(Customer)과 우수고객(VIPCustomer)에 따른 서비스를 제공하고자 합니다.
 *  	- 물품을 구매할 때 적용되는 할인율과 적립되는 포인트의 비율이 다릅니다.
 *  	- 멤버십에 대한 구현을 클래스 상속을 활용하여 구현하시오.
 * 
 * 
 */
public class CustomerMainT {

	public static void main(String[] args) {
		//일반 고객
		CustomerT customerLee = new CustomerT();
		customerLee.setName("이순신");
		customerLee.setPoint(1000);
		
		System.out.println("=== 일반 고객 정보 ===");
		System.out.println(customerLee.showInfo());
		
		//10,000원 상품 구매
		int price = 10000;
		int payAmount = customerLee.calcPrice(price);
		System.out.println("상품 가격 : "+price+"원");
		System.out.println("지불 금액 : "+payAmount+"원");
		System.out.println("구매 후 정보 : "+customerLee.showInfo());
		
		//VIP 고객
		VIPCustomerT customerShin = new VIPCustomerT();
		customerShin.setName("신사임당");
		customerShin.setPoint(10000);
		customerShin.setAgentID(12345);
			
		System.out.println();
		System.out.println("=== VIP 고객 정보 ===");
		System.out.println(customerShin.showInfo());
		
		//10,000원 상품 구매
		int vipPayment = customerShin.calcPrice(price);
		System.out.println("상품 가격 : "+price+"원");
		System.out.println("할인 적용 지불 금액 : "+vipPayment+"원 (10% 할인)");
		System.out.println("구매 후 정보 : "+customerShin.showInfo());
		System.out.println();
		
		System.out.println("=== 혜택 비교 ===");
		System.out.println("일반 고객 지불액 : "+payAmount);
		System.out.println("VIP 고객 지불액 : "+vipPayment);
		System.out.println("VIP 할인 혜택 : "+(payAmount-vipPayment));
		
		
		
		
	}
}
