package smartcar2;

/*
 * 추상 클래스 Car
 * 	- 모든 차량이 공통적으로 가져야할 기능을 정의함
 */
public abstract class Car {

	protected String name;
	protected double currentFuel;
	protected double distance;
	protected double tankSize;

	public Car(String name, int distance, int tankSize) {
		//super();
		this.name = name;
//		this.currentFuel = 100;
		this.currentFuel = ((tankSize-(distance/20))/tankSize)*100;
		this.distance = distance;
		this.tankSize = tankSize;
	}
	
	public abstract void startEngine();
	
	public abstract void drive();
	
	public abstract void stop();
	
	public void consumeFuel(int amount) {
		currentFuel -= amount;
		if(currentFuel < 0) currentFuel = 0;
		System.out.println(name + " : 현재 연료량 = "+currentFuel+"%");
	}
	
//	public void chargeFuel(int amount) {
//		currentFuel += amount;
//		if(currentFuel > 100) currentFuel = 100;
//		System.out.println(name + " : 현재 연료량 = "+currentFuel+"%");
//	}
	
	public void currentFuel() {
		System.out.println(name + " : 현재 연료량 = "+currentFuel+"%");
	};
	

	public String getName() {
		return name;
	}
	
	
	
	
}
