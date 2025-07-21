package smartcar2;

public class HybridCar extends Car {

	public HybridCar(String name, int distance, int tankSize) {
		super(name, distance, tankSize);
	}

	@Override
	public void startEngine() {
		System.out.println("하이브리드 시동 모드로 부르럽게 기동을 겁니다.");
		
	}

	@Override
	public void drive() {
		System.out.println("저속에서는 전기로, 고속에서는 가솔린으로 주행합니다.");
		this.currentFuel();
	}

	@Override
	public void stop() {
		System.out.println("회생 제동 시스템으로 정지합니다.");
		
	}

}
