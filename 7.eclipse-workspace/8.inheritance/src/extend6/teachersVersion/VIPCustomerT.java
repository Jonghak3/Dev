package extend6.teachersVersion;

/*
 * 제품을 살 때 10% 할인을 해 줌
 * 포인트는 제품 가격의 5% 적립해 줌
 * 담당 전문 상담원이 배정됨
 * 
 */
public class VIPCustomerT extends CustomerT {

	public VIPCustomerT(String id, String name, int agent) {
		super(id, name, agent);
		this.grade = "VIP";
		this.percent = 0.05;
		this.agent = 1234567;
	}

}
