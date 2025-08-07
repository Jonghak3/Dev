package constructor7;

public class StudentMain {

	public static void main(String[] args) {
		Student stu1 = new Student();
		
		Student stu2 = new Student("이순신");
		
		Student stu3 = new Student("신사임당", 20);
		
		Student stu4 = new Student("류성룡", 22, "컴퓨터공학 LLM");
		
		Student[] students = {stu1, stu2, stu3, stu4};
		
		for(Student stu:students) {
			stu.displayInfo();
		}
		System.out.println("\n=== 생성자 오버로딩 완료 ===");
	}
}
