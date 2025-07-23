package object;

class Student{
	int studentID;
	String studentName;
	public Student(int studentID, String studentName) {
		//super();
		this.studentID = studentID;
		this.studentName = studentName;
	}
	
	@Override
	public String toString() {
		
		return studentID+", "+studentName;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Student) {
			Student std = (Student)obj;
			if(this.studentID==std.studentID)	//학생의 학번이 같으면 true 반환
				return true;
			else
				return false;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		
		return studentID;	//해시 코드값으로 학번을 반환하도록 메서드 재정의
		
	}
	
	
}

public class EqualsDemo {

	public static void main(String[] args) {
		Student studentLee = new Student(100, "이순신");
		Student studentLee2 = studentLee;	//주소 복사
		
		Student studentShin = new Student(100, "신사임당");
		
		if(studentLee == studentLee2)
			System.out.println("studentLee와 studentLee2의 주소는 같다");
		else
			System.out.println("studentLee와 studentLee2의 주소는 다르다.");
		if(studentLee.equals(studentLee2)) 
			System.out.println("studentLee와 studentLee2는 동일합니다");
		else
			System.out.println("studentLee와 studentLee2는 동일하지 않습니다.");
		System.out.println();
		
		if(studentLee == studentShin)
			System.out.println("studentLee와 studentShin의 주소는 같다");
		else
			System.out.println("studentLee와 studentShin의 주소는 다르다.");
		if(studentLee.equals(studentShin)) 
			System.out.println("studentLee와 studentShin는 동일합니다");
		else
			System.out.println("studentLee와 studentShin는 동일하지 않습니다.");
		System.out.println();
		
		//두 학생 객체는 논리적으로 같기 때문에 같은 해시 코드값을 반환
		System.out.println("studentLee의 hashCode : "+ studentLee.hashCode());
		System.out.println("studentShin의 hashCode : "+ studentShin.hashCode());
		
		System.out.println("studentLee의 실제 주소값 : "+System.identityHashCode(studentLee));
		System.out.println("studentShin의 실제 주소값 : "+System.identityHashCode(studentShin));
		
	}
}
