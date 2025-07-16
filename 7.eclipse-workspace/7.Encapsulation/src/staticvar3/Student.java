package staticvar3;

public class Student {

	public static int serialNum = 2000;
	public int studentId;
	public String studentName;
	public int grade;
	public String address;
	
	public Student() {
		serialNum++;	//학생이 생성될 때마다 증가
		studentId = serialNum;	//증가된 값을 학번 인스턴스 변수에 부여
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
}
