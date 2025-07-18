package polymorphism.lab;

//직원 관리(급여) 시스템 

//부모 클래스
class Employee {
	protected String name;		//직원 이름
	protected int baseSalary;	//기본 급여
	
	public Employee(String name, int baseSalary) {
		//super();
		this.name = name;
		this.baseSalary = baseSalary;
	}
	
	// 급여 계산 메서드 
	public int calculateSalary() {
		return baseSalary;		//기본 급여 반환
	}
	
	//직원 정보 출력 메서드
	public void printEmployeeInfo() {
		System.out.println("=== 급여 계산서 ===");
		System.out.println("직원명 : "+name);
		System.out.println("기본급여 : "+baseSalary+"만원");
	}
}

//자식 클래스

//정규직 직원 클래스
class FullTimeEmployee extends Employee {
	private int bonus;

	public FullTimeEmployee(String name, int baseSalary, int bonus) {
		super(name, baseSalary);		//부모 생성자 호출
		this.bonus = bonus;
	}
	
	@Override
	public int calculateSalary() {
		return baseSalary+bonus;	//기본급 + 보너스
	}

	@Override
	public void printEmployeeInfo() {
		super.printEmployeeInfo();
		System.out.println("보너스 : "+bonus+"만원");
		System.out.println("총 급여 : "+calculateSalary()+"만원");
		System.out.println("고용형태 : 정규직");
		System.out.println("====================================");
		System.out.println();
	}			
}

//파트타임 직원 클래스
class PartTimeEmployee extends Employee {
	private int workHours;		//근무시간
	private int hourlyRate;		//시간당 급여
	
	public PartTimeEmployee(String name, int hourlyRate, int workHours) {
		super(name, 0);
		this.workHours = workHours;
		this.hourlyRate = hourlyRate;
	}

	@Override
	public int calculateSalary() {
		return hourlyRate*workHours;
	}

	@Override
	public void printEmployeeInfo() {
		System.out.println("=== 급여 계산서 ===");
		System.out.println("직원명 : "+name);
		System.out.println("시간당 급여 : "+hourlyRate+"만원");
		System.out.println("근무 시간 : "+workHours+"시간");
		System.out.println("총 급여 : "+calculateSalary()+"만원");
		System.out.println("고용형태 : 파트타임");
		System.out.println("====================================");
		System.out.println();
	}
	
	
	
}

//프리랜서 클래스
//속성 : 프로젝트 개수, 프로젝트당 수수료
//급여 : 프로젝트 개수 * 단가
class freelancerEmployee extends Employee {
	private int projectCount;
	private int projectPrice;
	
	public freelancerEmployee(String name, int projectCount, int projectPrice) {
		super(name, 0);
		this.projectCount = projectCount;
		this.projectPrice = projectPrice;
	}

	@Override
	public int calculateSalary() {
		return projectCount*projectPrice;
	}

	@Override
	public void printEmployeeInfo() {
		System.out.println("=== 급여 계산서 ===");
		System.out.println("직원명 : "+name);
		System.out.println("프로젝트 개수 : "+projectCount+"개");
		System.out.println("프로젝트 단가 : "+projectPrice+"만원");
		System.out.println("총 급여 : "+calculateSalary()+"만원");
		System.out.println("고용형태 : 프리랜서");
		System.out.println("====================================");
		System.out.println();
	}
		
}

//인턴 직원 클래스
//속성 : 인턴십 기간
//급여 계산 : 기본급*0.8
class inturn extends Employee {
	private int tenure;

	public inturn(String name, int baseSalary, int tenure) {
		super(name, baseSalary);
		this.tenure = tenure;
	}

	@Override
	public int calculateSalary() {
		return (int)(baseSalary*0.8);
	}

	@Override
	public void printEmployeeInfo() {
		System.out.println("=== 급여 계산서 ===");
		System.out.println("직원명 : "+name);
		System.out.println("인턴십 기간 : "+tenure+"개월");
		System.out.println("총 급여 : "+calculateSalary()+"만원");
		System.out.println("고용형태 : 인턴");
		System.out.println("====================================");
		System.out.println();
		
	}	
	
}

//계약직 직원 클래스 (ContractEmployee)
//속성 : 계약 개월 수(contractMonths). 계약 완료 보너스(contractBonus)
//급여 계산 : 기본급 + (계약 개월 수 따른 보너스)
//					--------------------
//				contractMonths >= 12 ? contractBonus : 0
class ContractEmployee extends Employee {
	private int contractMonths;
	private int contractBonus;
	
	public ContractEmployee(String name, int baseSalary, int contractMonths, int contractBonus) {
		super(name, baseSalary);
		this.contractMonths = contractMonths;
		this.contractBonus = contractBonus;
	}
	@Override
	public int calculateSalary() {	
		return baseSalary + (contractMonths>=12 ? contractBonus : 0);
	}
	@Override
	public void printEmployeeInfo() {
		System.out.println("=== 급여 계산서 ===");
		System.out.println("직원명 : "+name);
		System.out.println("기본급여 : "+baseSalary);
		System.out.println("계약 개월 수 : "+contractMonths+"개월");
		System.out.println("보너스 : "+contractBonus+"만원");
		System.out.println("총 급여 : "+calculateSalary()+"만원");
		System.out.println("고용형태 : 계약직");
		System.out.println("====================================");
		System.out.println();
	}
	
}


public class PolymorphismLab {

	public static void main(String[] args) {
		System.out.println("🏢 다형성 연습 - 직원 관리 시스템");
		System.out.println("=====================================");
		System.out.println("📊 개별 급여 계산 테스트");
		
		Employee emp1 = new FullTimeEmployee("이순신", 400 , 100);
		Employee emp2 = new PartTimeEmployee("이아르바이트", 10, 80);
		Employee emp3 = new freelancerEmployee("박프리", 3, 200);
		Employee emp4 = new ContractEmployee("최매니저", 600, 12, 200);
		Employee emp5 = new inturn("김인턴", 400, 4);
		
		//2.배열에 저장 (다형성 활용)
		Employee[] employees = {emp1, emp2, emp3, emp4, emp5};
		
		//3. 개별 급여 계산
		
		
		//4. 전체 급여 통계
		for(Employee employee : employees) {
			employee.calculateSalary();
			employee.printEmployeeInfo();
		}
		
		System.out.println("급여 통계");
		System.out.println("===============");
		System.out.println("총 직원 수: "+employees.length+"명");
		System.out.println("총 급여 지출: "++"만원");
		};
	}
