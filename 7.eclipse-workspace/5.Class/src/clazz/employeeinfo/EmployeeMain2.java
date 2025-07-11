package clazz.employeeinfo;

import java.util.Scanner;

public class EmployeeMain2 {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("등록할 직원 수를 입력하세요: ");
		int n = scanner.nextInt();
		Employee2[] employees = new Employee2[n];
		System.out.println();
		
		for(int i = 0; i < employees.length; i++) {
			System.out.println((i+1)+"번째 직원 정보를 입력하세요.");
			System.out.print("이름: ");
			String name = scanner.nextLine();
			scanner.nextLine();
			System.out.print("사번: ");
			int num = scanner.nextInt();
			System.out.print("급여: ");
			int salary = scanner.nextInt();
			System.out.print("성과 점수 (0.0 ~ 10.0): ");
			double score = scanner.nextDouble();
			System.out.print("정규직 여부(true/false): ");
			boolean isRegular = scanner.nextBoolean();
			System.out.print("부서 코드 (A:개발, B:마케팅, C:영업): ");
			String department = scanner.nextLine();
			
			employees[i] = createEmployee(name, num, salary, score, isRegular, department);
			
			printEmployees(employees);
			
			
		}
	
	}

	private static void printEmployees(Employee2[] employees) {
		System.out.println("=== 등록된 직원 정보 ===");
		for(int i = 0; i < employees.length;i++) {
			System.out.println((i+1)+". "+employees[i].name+
					" (사번: "+employees[i].num+")"
					);
			System.out.println("   급여: "+employees[i].salary+"원");
			System.out.println("   성과점수: "+employees[i].score+"점");
			System.out.println();
		}
		
	}

	private static Employee2 createEmployee(String name, int num, int salary, double score, boolean isRegular,
			String department) {
		Employee2 employee = new Employee2();
		employee.name = name;
		employee.num = num;
		employee.salary = salary;
		employee.score = score;
		employee.isRegular = isRegular;
		employee.department = department;
		return employee;
	}
}
