package smartcar;

public class Main {

	public static void main(String[] args) {
		CarManager manager = new CarManager();
		Car car1 = new ManualCar("제네시스80");
		Car car2 = new AICar("Moder 3 Zero");
		
		manager.registerCar(car1);
		manager.registerCar(car2);
		
		manager.runAllCars();
	}
}
