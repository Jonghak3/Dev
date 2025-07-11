package clazz.employeeinfo;

public class EmployeeMain {
	
	public static void main(String[] args) {
		Employee[] emp = new Employee[3];
		
		emp[0] = createEmp("이개발", 1001, 4500000, 8.5, true, "개발팀");
		emp[1] = createEmp("유마케팅", 1002, 4000000, 7.8, true, "마케팅팀");
		emp[2] = createEmp("신영업", 1003, 3800000, 9.2, false, "영업팀");
		
		System.out.println("=== 직원 정보 목록 ===");
		int totalSalary = 0;
		double totalScore = 0;
		
		
		for(int i = 0; i < emp.length; i++) {
			String isRegular = (emp[i].isRegular)?"정규직":"비정규직";
			System.out.println("이름: "+emp[i].name+", 사번: "+emp[i].num+", 급여: "+
								emp[i].salary+"원, 성과점수: "+emp[i].score +"점, 고용형태: "+
								isRegular+", 부서: "+emp[i].department);
			totalSalary += emp[i].salary;
			totalScore += emp[i].score;
		}
		System.out.println("전체 급여 총합: "+totalSalary+"원");
		System.out.println("평균 성과 점수: "+(totalScore/emp.length)+"점");
		
	}
	
	// 강사님이 하신 부서를 char 로 정의하고 그에 따라 부서분류
	public static String getDepartmentName(char deptCode) {
		switch(deptCode) {
		case 'A':
			return "개발팀";
		case 'B':
			return "마케팅팀";
		case 'C':
			return "영업팀";
		default :
			return "미지정";
		}
	}

	public static Employee createEmp(String name, int num, int salary, double score, boolean isRegular, String department) {
		Employee employee = new Employee();
		employee.name = name;
		employee.num = num;
		employee.salary = salary;
		employee.score = score;
		employee.isRegular = isRegular;
		employee.department = department;
		return employee;
	}
}
