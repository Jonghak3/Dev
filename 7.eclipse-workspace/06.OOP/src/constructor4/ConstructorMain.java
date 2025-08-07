package constructor4;

public class ConstructorMain {

	public static void main(String[] args) {
		//1. 기본 생성자가 자동으로 제공되는 경우,
		System.out.println("1. SimpleAccount - 자동 기본 생성자");
		SimpleAccount simpleAccount = new SimpleAccount();
		simpleAccount.printInfo();	//모든 값이 기본값 (null, 0)
		
		//2. 기본 생성자가 제공되지 않는 경우
		System.out.println("\n2. CustomAccount - 매개변수 생성자만 존재");
		//CustomAccount cumtom = new CustomAccount();	// 컴파일 에러
		CustomAccount customAccount = new CustomAccount("custom-001", "신사임당", 25000);
		customAccount.printInfo();
		
		//3. 명시적으로 기본 생성자를 정의한 경우
		System.out.println("\n3. FlexibleAccount - 기본 생성자, 매개변수 생성자 둘다 존재");
		//기본 생성자 사용
		FlexibleAccount simpleAccount2 = new FlexibleAccount();
		simpleAccount2.printInfo();
		//setAccountInfo()
		simpleAccount2.setAccountInfo("FLEX-001", "이순신", 40000);
		simpleAccount2.printInfo();
		//매개변수 생성자 사용
		FlexibleAccount customAccount2 = new FlexibleAccount("custom-002", "이순신", 50000);
		customAccount2.printInfo();
		
		System.out.println("\n=== 생성자 정리 ===");
		System.out.println("* 생성자를 정의하지 않으면 기본 생성자가 자동 생성됨");
		System.out.println("* 매개변수가 있는 생성자를 정의하면 기본 생성자가 자동 생성되지 않음");
		System.out.println("* 필요하면 기본 생성자를 명시적으로 정의해야 함");
		System.out.println("* 생성자는 객체가 생성될 때 반드시 호출됨");
	}
}
