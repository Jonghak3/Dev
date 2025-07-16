package constructor7;

public class Student {

	String name;
	int age;
	String major;
	
	public Student() {
		this("", 0, "");
	}

	public Student(String name, int age, String major) {
		//super();
		this.name = name;
		this.age = age;
		this.major = major;
	}

	public Student(String name) {
		//super();
		this(name, 0, "");
	}

	public Student(String name, int age) {
		//super();
		this(name, age, "");
	}
	void displayInfo() {
		System.out.println("이름: "+name+", 나이: "+age+", 전공: "+major);
	}
	
	
	
	
}
