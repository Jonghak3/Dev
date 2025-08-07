package extend6.teachersVersion;

/*
 * 제품을 살 때 10% 할인을 해 줌
 * 포인트는 제품 가격의 5% 적립해 줌
 * 담당 전문 상담원이 배정됨
 * 
 */
public class VIPCustomerT extends CustomerT {
	
	private int agentID;
	private double salesRatio;
	
	public VIPCustomerT() {
		grade = "VIP";
		percent = 0.05;		//5% 보너스 적립
		salesRatio = 0.1;	//10% 할인
	}

	public int getAgentID() {
		return agentID;
	}

	public void setAgentID(int agentID) {
		this.agentID = agentID;
	}

	public double getSalesRatio() {
		return salesRatio;
	}

	public void setSalesRatio(double salesRatio) {
		this.salesRatio = salesRatio;
	}

	@Override		//VIP 고객 : 10% 할인 + 5% 보너스 적립
	public int calcPrice(int price) {
		point += price*percent;
		return price-(int)(price*salesRatio);
	}

	@Override
	public String showInfo() {
		
		return name+"님의 등급은 "+grade+"이며, 보너스 포인트는 "+
				point+"이고, 담당 상담원 번호는 "+agentID+"입니다.";
	}
	
	
}
