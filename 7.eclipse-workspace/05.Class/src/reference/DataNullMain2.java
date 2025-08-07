package reference;

public class DataNullMain2 {

	public static void main(String[] args) {
		Data data =null;
		data.value = 10;	//NullpointerException 예외 발생
		System.out.println("data = "+ data.value);
	}
}
