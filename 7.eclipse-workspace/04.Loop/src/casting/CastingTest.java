package casting;

public class CastingTest {

	public static void main(String[] args) {
		System.out.println("=== 1. 웹 폼 데이터 처리 ===");
		webFormDataProcessing();
	}

	private static void webFormDataProcessing() {
		// 웹 폼에서 받은 사용자 입력 데이터
		String[] userAges = {"25", "30", "35", "28", "45"};	//나이 데이터
		String[] salaryInputs = {"35000000", "42000000", "51000000", "38000000", "62000000"};	//연봉 데이터
		
		int totalAge = 0;
		System.out.println("사용자 입력 데이터를 숫자로 변환하여 처리");
		for(int i = 0; i < userAges.length; i++) {
			int age = Integer.parseInt(userAges[i]); //String => int
			totalAge += age;	//누적 합계
			System.out.println(" 사용자 "+(i + 1)+" : " + age+"세");
		}
		
		//평균 나이 계산
		double avgAge = (double) totalAge/userAges.length;	//명시적 캐스팅으로 정확한 소수점 계산 
		System.out.println("📊 평균 나이 : " + avgAge + "세");
		
		// 연봉 데이터 처리
		System.out.println("\n 🔄 연봉 데이트 처리: ");
		long totalSalary = 0;
		for(int i = 0; i < salaryInputs.length; i++) {
			int salary = Integer.parseInt(salaryInputs[i]); //String => int
			totalSalary += salary;		//int => long 자동 형변환
			System.out.println(" 직원 "+(i + 1)+" : "+ salary+"원");
		}
		System.out.println(" 💰 총 연봉 합계 : " +totalSalary+"원");
	}
}
