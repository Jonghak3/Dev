package extend6.teachersVersion;

/*
 * 속성 : 고객 아이디, 고객 이름, 고객 등급, 포인트, 포인트 적립비율
 * 기능 : showInfo() 
 * 		 물품 구매 시 1% 포인트 적립
 * 
 */
public class CustomerT {
	protected int id;
	protected String name;
	protected String grade;
	protected int point;
	protected double percent;
	protected int agent;
	
	public CustomerT() {
		this.grade = "silver";
		this.percent = 0.01;
	}
	
	public CustomerT(int id, String name, int point) {
		super();
		this.id = id;
		this.name = name;
		this.grade = "silver";
		this.percent = 0.01;
		this.agent = 0;
		this.point = point;
	}
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getGrade() {
		return grade;
	}



	public void setGrade(String grade) {
		this.grade = grade;
	}



	public int getPoint() {
		return point;
	}



	public void setPoint(int point) {
		this.point = point;
	}



	public double getPercent() {
		return percent;
	}



	public void setPercent(double percent) {
		this.percent = percent;
	}



	public int getAgent() {
		return agent;
	}



	public void setAgent(int agent) {
		this.agent = agent;
	}
	
	//일반 고객 : 할인없음. 1% 보너스 적립
	public int calcPrice(int price) {
		point += price*percent;
		return price;
	}
	
	public String showInfo() {
		return name+"님의 등급은 "+grade+"이며, 보너스 포인트는 "+
				point+"입니다.";
	}
	
	
}
