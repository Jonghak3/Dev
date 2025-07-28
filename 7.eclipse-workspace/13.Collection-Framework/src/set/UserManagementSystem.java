package set;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
 * - HashSet 활용한 중복 방지 및 빠른 검색
 *	 메뉴 기반 콘솔 애플리케이션
 */
public class UserManagementSystem {

	//데이터베이스나 외부 저장소를 사용하는 대신 메모리에 저장
	private static Set<String> registeredUsers = new HashSet<String>();
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		//초기 데이터 로딩 (db에서는 SELECT * FROM users;)
		initializeUsers();
		
		while(true) {
			displayMene();
			
			System.out.println("\n=== 사용자 관리 시스템 ===");
			System.out.println("1. 신규 사용자 등록");
			System.out.println("2. 사용자 조회");
			System.out.println("3. 사용자 삭제");
			System.out.println("4. 전체 사용자 목록");
			System.out.println("5. 사용자 수 확인");
			System.out.println("0. 종료");
			System.out.println("선택: ");
			
			//숫자 입력 예외 처리
			int choice;
			try {
				choice = scanner.nextInt();
				scanner.nextLine();		//개행 문자 처리
				
			} catch (Exception e) {
				System.out.println("❌ 숫자를 입력해주세요.");
				scanner.nextLine();		//잘못된 입력 제거
				continue;				//다시 메뉴로
			}
			
			switch(choice) {
				case 1: 
					registeredUser(scanner);
					break;
				case 2: 
					searchUser(scanner);
					break;
				case 3:
					deleteUser(scanner);
					break;
				case 4:
					displayAllUsers();
					break;
				case 5:
					displayUserCount();
					break;
				case 0:
					System.out.println("시스템을 종료합니다.");
					scanner.close();
					return;	//main 메서드 종료
				default:
					System.out.println("❌ 잘못된 선택입니다. (0-5 사이의 숫자를 입력하세요.)");
			}
			
		}
	}

	/*
	 * size() : HashSet의 현재 요소 개수 반환
	 */
	private static void displayUserCount() {
		int userCount = registeredUsers.size();
		
		System.out.println("📊 현재 등록된 사용자 수: "+registeredUsers.size()+"명");
		
		if(userCount == 0) {
			System.out.println("💡 사용자를 등록해보세요! (메뉴 1번 선택)");
		} else if(userCount >= 100) {
			System.out.println("⚠️ 사용자 수가 많습니다. 성능 최적화를 고려해보세요. ");
		}
	}

	private static void displayAllUsers() {
		if(registeredUsers.isEmpty()) {
			System.out.println("📝 등록된 사용자가 없습니다.");
			return;
		}
		
		System.out.println("=== 등록된 사용자 목록 ===");
		int index = 1;		//인덱스 번호 표시
		for(String user :registeredUsers) {
			System.out.println(index++ +"." +user);
		}
		
		System.out.println("-".repeat(30));
		System.out.println("총 "+registeredUsers.size()+"명의 사용자가 등록되어 있습니다");
	}

	/*
	 * 사용자 삭제하는 메서드
	 */
	private static void deleteUser(Scanner scanner) {
		System.out.print("삭제할 사용자 ID를 입력하세요");
		String userId = scanner.nextLine().trim();
		
		//빈 입력 체크
		if(userId.isEmpty()) {
			System.out.println("❌ 사용자 ID를 입력해주세요.");
			return;
		}
		
		//중요 계정 삭제 방지
		if("admin".equals(userId)) {
			System.out.println("⚠️ 관리자 계정은 삭제할 수 없습니다.");
			return;
		}
		
		if(registeredUsers.remove(userId)) {
			System.out.println("✅ 사용자 '"+userId+"'가 삭제되었습니다.");
		} else {
			System.out.println("❌ 삭제할 사용자 '"+userId+"'를 찾을 수 없습니다.");
		}
	}

	/*
	 * 사용자 존재 여부 조회하는 메서드
	 * boolean contains(Object o)
	 */
	private static void searchUser(Scanner scanner) {
		System.out.print("조회할 사용자 ID를 입력하세요");
		String userId = scanner.nextLine().trim();
		
		//빈 입력 체크
		if(userId.isEmpty()) {
			System.out.println("❌ 사용자 ID를 입력해주세요.");
			return;
		}
		
		if(registeredUsers.contains(userId)) {
			System.out.println("✅ 사용자 '"+userId+"'가 시스템에 등록되어 있습니다.");
		} else {
			System.out.println("❌ 사용자 '"+userId+"'을(를) 찾을 수 없습니다.");
		}
		
	}

	/*
	 * 입력 검증 
	 */
	private static void registeredUser(Scanner scanner) {
		System.out.print("등록할 사용자 ID를 입력하세요: ");
		String userId = scanner.nextLine().trim();	//trim()으로 앞뒤 공백 제거
		
		//Validation
		if(userId.isEmpty()) {
			System.out.println("❌ 사용자 ID는 비워둘 수 없습니다.");
			return;
		}
		
		if(userId.length() < 3) {
			System.out.println("❌ 사용자 ID는 3자 이상이어야 합니다.");
			return;
		}
		
		if(userId.contains(" ")) {
			System.out.println("❌ 사용자 ID에는 공백이 포함될 수 없습니다.");
		}
		
		//HashSet의 add() 메서드 호출
		if(registeredUsers.add(userId)) {
			System.out.println("✅ 사용자 '"+userId+"'가 성공적으로 등록되었습니다.");
			//(데이터베이스 INSERT 하는 부분)
		} else {
			System.out.println(" 이미 존재하는 사용자 ID입니다.: "+userId);
		}
		
	}

	/*
	 * 유지보수성 
	 */
	private static void displayMene() {
		System.out.println("\n"+"=".repeat(40));
		System.out.println("🏢 사용자 관리 시스템 v1.0");
		System.out.println("\n"+"=".repeat(40));
		
	}

	/*
	 * 대용랑 데이터의 경우 페이징 처리 필요
	 */
	private static void initializeUsers() {
		registeredUsers.add("admin");
		registeredUsers.add("user01");
		registeredUsers.add("manager");
		registeredUsers.add("developer");
		registeredUsers.add("tester");
		
		System.out.println("✅ 초기 사용자 데이터가 로드되었습니다. "
				             + "(총 "+registeredUsers.size()+"명)");
		
	}
}
