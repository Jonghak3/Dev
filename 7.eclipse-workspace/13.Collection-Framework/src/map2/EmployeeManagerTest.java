package map2;

import java.util.List;

public class EmployeeManagerTest {

	public static void main(String[] args) {
		EmployeeManger manager = new EmployeeManger();
		
		System.out.println("직원 관리 시스템(HashMap)");
		System.out.println("=".repeat(50));
		
		//1. 직원데이터 추가
		addEmployees(manager);
		
		//2. 전체 직원 조회
		showAllEmployees(manager);
		
		//3. 개별 직원 조회
		findEmployee(manager);
		
		//4. 직원 정보 수정
		updateEmployee(manager);
		
		//5. 직원 삭제
		removeEmployee(manager);
		
	}
	
	private static void removeEmployee(EmployeeManger manager) {
		System.out.println("\n 5. 직원 삭제");
		System.out.println("-".repeat(30));		
		
		System.out.println("📌 emp005 삭제 처리: ");
		Employee removed = manager.removeEmployee("emp005");
		
		//삭제 후 전체 목록 확인
		System.out.println("\n 삭제 후 직원 목록: ");
		manager.showAllEmployees();
	}

	private static void updateEmployee(EmployeeManger manager) {
		System.out.println("\n 4. 직원 정보 수정");
		System.out.println("-".repeat(30));
		
		//승진 및 급여 인상
		System.out.println("📌 emp001 승진 처리: ");
		manager.updateEmployee("emp001", "대리", 50000000);
		
		//수정된 정보 확인
		Employee employee = manager.findEmployeeById("emp001");
		if(employee != null) {
			System.out.println("수정 후: "+employee);
		}
	}

	private static void findEmployee(EmployeeManger manager) {
		System.out.println("\n 3. 직원 검색");
		System.out.println("-".repeat(30));
		
		//사번으로 검색
		System.out.println("📌 사번으로 검색: ");
		Employee found = manager.findEmployeeById("emp003");
		if(found != null) {
			System.out.println("검색 결과: "+found);
		}
		
		//존재하지 않는 사변
		Employee notFound = manager.findEmployeeById("emp9999");
		System.out.println("존재하지 않는 사원 검색 결과: "+ 
				(notFound == null ? "직원을 찾을 수 없습니다" : notFound));
		
		//부분 일치
		System.out.println("\n📌 이름으로 검색 ('이' 포함):");
		List<Employee> searchResult = manager.findEmployeeByName("이");
		if(searchResult.isEmpty()) {
			System.out.println("검색 결과가 없습니다.,");
		} else {
			searchResult.forEach(System.out::println);
		}
	}

	private static void showAllEmployees(EmployeeManger manager) {
		System.out.println("\n 2. 전체 직원 조회");
		System.out.println("-".repeat(30));
		
		manager.showAllEmployees();
	}

	private static void addEmployees(EmployeeManger manager) {
		System.out.println("\n 1. 직원 추가");
		System.out.println("-".repeat(30));
		
		//
		Employee emp1 = new Employee("emp001", "이방원", "개발팀", "주임", 45000000, "Lee@gmail.com");
		Employee emp2 = new Employee("emp002", "신사임당", "마케팅팀", "대리", 48000000, "Shin@gmail.com");
		Employee emp3 = new Employee("emp003", "류성룡", "개발팀", "선임", 52000000, "Ryu@gmail.com");
		Employee emp4 = new Employee("emp004", "손흥민", "인사팀", "과장", 60000000, "Son@gmail.com");
		Employee emp5 = new Employee("emp005", "이강", "개발팀", "사원", 38000000, "Han@gmail.com");
		
		manager.addEmployee(emp1);
		manager.addEmployee(emp2);
		manager.addEmployee(emp3);
		manager.addEmployee(emp4);
		manager.addEmployee(emp5);
		
		Employee duplicatedEmp = new Employee("emp001", "이순신", "테스트팀");
		manager.addEmployee(duplicatedEmp);
		
	}
}
