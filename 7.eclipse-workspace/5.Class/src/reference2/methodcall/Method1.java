package reference2.methodcall;

public class Method1 {

	public static void main(String[] args) {
		Student student1 = new Student();
		initStudent(student1, "이순신1", 15, 90);
		
		Student student2 = new Student();
		initStudent(student2, "신사임당1", 16, 95);
		
		printStudent(student1);
		printStudent(student2);
		
		
	}

	private static void printStudent(Student student) {
		System.out.println("이름 : "+student.name+" 나이 : "+
				student.age+ ", 성적 : "+student.grade);
		
	}

	private static void initStudent(Student student, String name, int age, int grade) {
		
		student.name = name;
		student.age = age;
		student.grade = grade;
	}
}
