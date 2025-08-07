package clazz.employeeinfo;

import java.util.Scanner;

public class EmployeeMain2 {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		//등록할 직원 수 입력 받기
		System.out.print("등록할 직원 수를 입력하세요: ");
		int n = scanner.nextInt();
		scanner.nextLine();		//입력 버퍼 정리
		
		//직원 배열 생성
		Employee2[] employees = new Employee2[n];
		System.out.println();
		
		for(int i = 0; i < employees.length; i++) {
			System.out.println((i+1)+"번째 직원 정보를 입력하세요.");
			System.out.print("이름: ");
			String name = scanner.nextLine();
			System.out.print("사번: ");
			int num = scanner.nextInt();
			System.out.print("급여: ");
			int salary = scanner.nextInt();
			System.out.print("성과 점수 (0.0 ~ 10.0): ");
			double score = scanner.nextDouble();
			System.out.print("정규직 여부(true/false): ");
			boolean isRegular = scanner.nextBoolean();
			System.out.print("부서 코드 (A:개발, B:마케팅, C:영업): ");
			char department = scanner.next().charAt(0);
			//String department = scanner.nextLine();
			scanner.nextLine();		//입력 버퍼 정리
			System.out.println();
			System.out.println();
			
			
			//입력받은 정보로 직원 객체 생성
			employees[i] = createEmployee(name, num, salary, score, isRegular, department);
		}	
		
		//모든 직원 정보 출력
		printEmployees(employees);
		
		//통계 점수 출력
		printStatistics(employees);
			
			
		scanner.close();
	
	}
	// 통계 점수 출력 메서드
	public static void printStatistics(Employee2[] employees) {
		System.out.println("=== 통계 정보 ===");
		
		//전체 급여 총합
		int totalSalary = getTotalSalary(employees);
		System.out.println("전체 급여 총합 : "+totalSalary+"원");
		
		//평균 급여
		double averageSalary = (double)totalSalary / employees.length;
		System.out.println("평균 급여 : "+(int)averageSalary+"원");
		
		//평균 성과 점수 계산
		double averageScore = getAveragePerformanceScore(employees);
		System.out.printf("평균 성과 점수 : %.1f점\n", averageScore);
		
		//정규직 비율 계산
		int fullTimeCount = 0;		//정규직 수 카운트
		for(Employee2 employee : employees) {
			if(employee.isRegular) {	//boolean 변수 조건 확인
				fullTimeCount++;
			}
		}
		double fullTimeRatio = (double)fullTimeCount / employees.length*100;
		System.out.printf("정규직 비율: %.1f%%\n", fullTimeRatio);
		
		
	}

	private static double getAveragePerformanceScore(Employee2[] employees) {
		double totalScore = 0;
		for(Employee2 employee : employees) {
			totalScore += employee.score;
		}
		return totalScore / employees.length;
	}
	private static int getTotalSalary(Employee2[] employees) {
		int totalSalary = 0;
		for(Employee2 employee : employees) {
			totalSalary += employee.salary;
		}
		return totalSalary;
	}
	public static void printEmployees(Employee2[] employees) {
		System.out.println("=== 등록된 직원 정보 ===");
		int idx = 1;
		for(Employee2 employee : employees) {
			System.out.println(idx+". "+employee.name+
					" (사번: "+employee.num+")"
					);
			System.out.println("   급여: "+employee.salary+"원");
			System.out.println("   성과점수: "+employee.score+"점");
			System.out.println("   고용형태: "+(employee.isRegular ? "정규직":"비정규직"));
			System.out.println("   부서: "+ getDepartmentName(employee.department));
			idx++;
		}
//		for(int i = 0; i < employees.length;i++) {
//			String isRegular = (employees[i].isRegular)?"정규직":"비정규직";
//			System.out.println((i+1)+". "+employees[i].name+
//					" (사번: "+employees[i].num+")"
//					);
//			System.out.println("   급여: "+employees[i].salary+"원");
//			System.out.println("   성과점수: "+employees[i].score+"점");
//			System.out.println("   고용형태: "+isRegular);
//			System.out.println("   부서: "+employees[i].department);
//		}
		
	}

	private static String getDepartmentName(char deptCode) {
		switch(deptCode) {
			case 'A' :
				return "개발팀";
			case 'B' :
				return "마케팅팀";
			case 'C' :
				return "영업팀";
			default :
				return "미지정";
		}
	}
	private static Employee2 createEmployee(String name, int num, int salary, double score, boolean isRegular,
			char department) {
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
