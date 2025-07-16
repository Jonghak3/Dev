package constructor5;

public class MemberConstructor {
	String name;
	int age;
	int grade;
	
	public MemberConstructor(String name, int age, int grade) {
		//super();
		this.name = name;
		this.age = age;
		this.grade = grade;
	}

	public MemberConstructor(String name, int age) {
		//super();
//		this.name = name;
//		this.age = age;	
//		this.grade = 50;
//		System.out.println("123");
		this(name, age, 50);	//생성자 내부에서 또다른 생성자를 호출함 =>코드 중복 제거.
	}
	
	
	
}
