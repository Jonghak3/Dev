package condition;

public class LambdaSwitchLab {

	public static void main(String[] args) {
		System.out.println("=== switch 구문, 람다식 구문 ===");
		
		// 데이터 배열
		String[] roles = {"관리자", "사용자", "게스트", "VIP", "알 수 없음"};
		/*
		 * 관리자 레벨 1: JUNIOR_ADMIN
		 * 관리자 레벨 3: REGULAR_ADMIN
		 * 관리자 레벨 5: SENIOR_ADMIN
		 * 관리자 레벨 8: SUPER_ADMIN
		 */
		int[] levels = {1, 3, 5, 8};
		
		System.out.println();
		System.out.println(" 테스트 데이터: ");
		System.out.println(" 역할 배열: ");
		System.out.println(" 레벨 배열: ");
		System.out.println("=============================\n");
		
		/*
		 * for-each 구문
		 * 	for(타입 변수명 : 배열 또는 컬렉션){
		 * 		//반복 실행할 코드
		 * } 
		 */
		
		// 전통적 switch구문 (java 1.0+)
		System.out.println("모든 역할에 대한 전통적 switch 구문");
		for(String role : roles) {
			System.out.println("\n>>>>> 테스트 역할: " + role);
			traditionalSwitch(role);
		}
		System.out.println("\n\n");
		
		// 람다식 switch구문 (java 14+)
		System.out.println("모든 역할에 대한 람다식 switch 구문");
//		for(int i = 0; i < roles.length; i++) {
//			System.out.println("\n>>>>> 테스트 역할: " + roles[i]);
//			lambdaSwitch(roles[i]);
//		}
		for(String role : roles) {
			System.out.println("\n>>>> === "+ role + "의 레벨별 차이 ===");
			for(int level : levels) {
				System.out.println("\n>>>> "+role+" 레벨 "+level+":");
				lambdaSwitchStatement(role, level);
			}
		}
	}

	private static void lambdaSwitchStatement(String role, int level) {
		System.out.println("☝️ 2. lambda switch 구문 (Java 14+)");
		System.out.println("     특징: break 불필요, 동작 수행");
		
//		switch(role) {
//			case "관리자" -> System.out.println("   👑 관리자로 로그인 완료");
//			case "사용자" -> System.out.println("   👤 사용자로 로그인 완료");
//			case "게스트" -> System.out.println("   🎭 게스트로 로그인 완료");
//			default -> System.out.println("   ❓ 기본 로그인 완료");
//		}
		switch(role) {
			case "관리자" -> {
				System.out.println("         🔧관리자 설정 로딩 중....");
				System.out.println("         📊시스템 상태 체크 중....");
				performAdminTasks(level);
			}
			case "사용자" -> {
				System.out.println("         🔧개인 설정 로딩 중....");
				System.out.println("         📊사용자 데이터 동기화 중....");
				performUserTasks(level);
			}
			case "게스트" -> {
				System.out.println("         🔧체험 모드 로딩 중....");
				performGuestTasks(level);
			}
			default -> System.out.println("      ↪️ 기본 설정 적용");
		}
		
		System.out.println("===========================================\n");
		
	}

	private static void performGuestTasks(int level) {
		System.out.println("         🔧게스트 작업 수행 (레벨:"+level+")");
		
	}

	private static void performUserTasks(int level) {
		System.out.println("         🔧사용자 작업 수행 (레벨:"+level+")");
		
	}

	private static void performAdminTasks(int level) {
		System.out.println("         🔧관리자 작업 수행 (레벨:"+level+")");
		
	}

	private static void traditionalSwitch(String role) {
		//System.out.println("☝️ 1. 전통적 switch 구문");
		
		switch(role) {
			case "관리자":
				System.out.println(" 	👑 관리자 권한으로 로그인");
				System.out.println(" 	📊 관리자 대시보드 로딩");
				break;
			case "사용자":
				System.out.println(" 	👤 사용자 권한으로 로그인");
				break;
			case "게스트":
				System.out.println(" 	🎭 게스트 모드로 진입");
				break;
			default:
				System.out.println(" 	❓ 알 수 없는 역할");
				break;
		}
		
	}
}
