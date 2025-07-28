package list2;

public class Student {
	private String name;
	private int age;
	private String major;
	private double gpa;	//학점평균
	
	public Student(String name, int age, String major, double gpa) {
		//super();
		this.name = name;
		setAge(age);	//setter에서 유효성 검사
		this.major = major;
		setGpa(gpa);	//setter에서 유효성 검사
	}

	public String getName() {	
		return name;
	}

	public int getAge() {
		//나이 유효성 검증
		if(age < 0 || age > 150) {
			throw new IllegalArgumentException("나이는 0 이상 150 이하여야 합니다.");
		}
		return age;
	}

	public String getMajor() {
		return major;
	}

	public double getGpa() {
		//Gpa 유효성 검증
		if(gpa < 0.0 || gpa > 4.0) {
			throw new IllegalArgumentException("학점은 0.0 이상 4.0 이하여야 합니다.");
		}
		return gpa;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	@Override
	public String toString() {
		//return "Student [name=" + name + ", age=" + age + ", major=" + major + ", gpa=" + gpa + "]";
		return String.format("Student {name='%s', age=%d, major='%s', gpa=%.1f}", 
										name, age, major, gpa);
	}
	
	
	
	
}
