package smartcar2;

public class Main {

	public static void main(String[] args) {
		CarManager manager = new CarManager();
		Car car1 = new ManualCar("제네시스80", 500, 50);
		Car car2 = new AICar("Moder 3 Zero", 100, 50);
		Car car3 = new HybridCar("책티언", 300, 50);
		
		manager.registerCar(car1);
		manager.registerCar(car2);
		manager.registerCar(car3);
		
		manager.runAllCars();
	}
}
