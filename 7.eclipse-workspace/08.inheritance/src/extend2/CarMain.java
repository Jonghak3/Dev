package extend2;

public class CarMain {
	
	public static void main(String[] args) {
		PickupCar pickupCar = new PickupCar();
		pickupCar.move();
		pickupCar.pickup();
		pickupCar.openCar();
		
		ElectricCar electricCar = new ElectricCar();
		electricCar.move();
		electricCar.charge();
		electricCar.openCar();
		
		HydrogenCar hydrogenCar = new HydrogenCar();
		hydrogenCar.move();
		hydrogenCar.fillHydrogen();
		hydrogenCar.openCar();
	}
}
