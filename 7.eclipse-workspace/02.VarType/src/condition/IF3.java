package condition;

public class IF3 {

	public static void main(String[] args) {
		int age = 15;
		
		if(age <= 8) {
			System.out.println("미취학");
		} else if (age <= 13) {
			System.out.println("초등학교");
		} else if (age <= 16) {
			System.out.println("중학교");
		} else if (age <= 19) {
			System.out.println("고등학교");
		} else {
			System.out.println("성인");
		}
	}
}
