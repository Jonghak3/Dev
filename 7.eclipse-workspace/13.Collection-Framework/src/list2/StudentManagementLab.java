package list2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/*
 * 학생 관리 시스템
 * 	- ArrayList를 활용한 CRUD 작업
 */
public class StudentManagementLab {
	
	//전역변수
	private static List<Student> students = new ArrayList<>();
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		//초기 데이터 생성(DB에서 가져올 데이터)
		initializeData();
		
		int choice = 0;
		do {
			//메뉴 출력
			printMenu();
			choice = scanner.nextInt();
			scanner.nextLine();	//버퍼 비우기
			
			switch(choice) {
				case 0: System.out.println("프로그램을 종료합니다"); break;
				case 1: addStudents(); break;
				case 2: viewAllStudents(); break;
				case 3: searchStudent(); break;
				case 4: updateStudent(); break;
				case 5: removeStudent(); break;
				default: System.out.println("잘못된 선택입니다.");
			}
			
		} while(choice != 0);
		
		scanner.close();
	}
	
	/*
	 * remove()
	 */
	private static void removeStudent() {
		System.out.println("🐥 삭제할 학생 이름: ");
		String targetName = scanner.nextLine();
		
		Iterator<Student> itr = students.iterator();
		boolean removed = false;		//삭제 성공 여부 저장하는 변수(flag)
		while(itr.hasNext()) {
			Student student = itr.next();
			if(student.getName().equals(targetName)) {
				//삭제 확인 과정
				System.out.printf("⚠️ 정말로 '%s' 학생을 삭제하시겠습니까? (y/n): ", targetName);
				String confirm = scanner.nextLine();
				
				if(confirm.toLowerCase().equals("y")||confirm.equals("예")) {
					itr.remove();
					System.out.println("✅'"+targetName+"' 학생이 삭제되었습니다.");
					removed = true;
				} else {
					System.out.println("❌ 삭제가 취소되었습니다.");
					removed = true;		//찾았으므로 true로 설정
				}
				break;	//찾았으므로 loof 종료
			}
		}
		
		//학생을 찾지 못한 경우
		if(!removed) {
			System.out.println("❌ 해당 학생을 찾을 수 없습니다.1");
		} else {
			System.out.println("🏢 현재 총 학생 수: "+students.size());
		}
		
		
	}

	/*
	 * 기존 데이터 검색 후 수정
	 * 현재 정보를 보여준 후 새 정보 입력받기
	 * 유효성 검증
	 */
	private static void updateStudent() {
		System.out.println("🔧 수정할 학생 이름 : ");
		String targetName = scanner.nextLine();
		
		//수정할 학생 찾기 
		Student targetStudent = null;	//찾은 학생 객체를 저장할 변수
		
		for(Student student : students) {
			if(student.getName().equals(targetName)) {
				targetStudent = student;
				break;		//찾으면 루프 종료
			}
		}
		
		// 해당 학생을 찾지 못한 경우의 처리
		if(targetStudent == null) {
			System.out.println("❌ 해당 학생을 찾을 수 없습니다.");
			System.out.println("💡 '2.전체 학생 조회'에서 등록된 학생을 확인해보세요.");
			return;	
		}
		
		//현재 정보 출력 - 사용자가 현재 값을 확인할 수 있도록함
		System.out.println("\n 📜 현재 정보: ");
		System.out.println("-".repeat(50));
		System.out.printf("이름: %s\n", targetStudent.getName());
		System.out.printf("나이: %d\n", targetStudent.getAge());
		System.out.printf("전공: %s\n", targetStudent.getMajor());
		System.out.printf("학점: %.1f\n", targetStudent.getGpa());
		System.out.println("-".repeat(50));
		
		//수정 (유효성 검증 포함)
		int newAge;
		while(true) {
			System.out.println("새로운 나이 (현재: "+targetStudent.getAge()+"):");
			try {
				newAge = scanner.nextInt();
				scanner.nextLine();
				
				if(newAge >= 0 && newAge <=150) {
					break;
				} else {
					System.out.println("❌ 나이는 0 이상 150 이하여야 합니다. 다시 입력해주세요.");
				}
			} catch (Exception e) {
				System.out.println("❌ 숫자만 입력해주세요!");
				scanner.nextLine();		//잘못된 입력 버퍼 지우기
			}
		}
		//전공 수정
		System.out.print("새로운 전공 (현재: "+targetStudent.getMajor()+"):");
		String newMajor = scanner.nextLine();
		
		//학점 수정
		double newGpa;
		while(true) {
			System.out.println("새로운 학점 (현재: "+targetStudent.getGpa()+"):");
			try {
				newGpa = scanner.nextDouble();
				scanner.nextLine();
				if(newGpa >= 0.0 && newGpa<=4.0) {
					break;
				} else {
					System.out.println("❌ 학점은 0.0 이상 4.0 이하여야 합니다. 다시 입력해주세요.");
				}
			} catch (Exception e) {
				System.out.println("❌ 숫자만 입력해주세요!");
				scanner.nextLine();		//잘못된 입력 버퍼 지우기
			}
		}
		
		//setter()
		targetStudent.setAge(newAge);;
		targetStudent.setMajor(newMajor);
		targetStudent.setGpa(newGpa);
		
		//성공 메시지 출력
		System.out.println("✅ 학생 '"+targetName+"'의 정보가 성공적으로 수정되었습니다!");
	}

	/*
	 * 부분 문자열 검색 (contains())
	 * 검색 결과를 별도 리스트에 저장
	 */
	private static void searchStudent() {
		System.out.print("🔍 검색할 학생 이름(부분 검색 가능) : ");
		String searchName = scanner.nextLine();
		
		//지역변수로 별개의 임시 저장소 리스트 생성
		List<Student> foundStudents = new ArrayList<>();
		
		//전체 학생 리스트에서 검색어가 포함된 학생 찾기 
		for(Student student : students) {
			if(student.getName().toLowerCase().contains(searchName.toLowerCase()))
				foundStudents.add(student);		//검색 조건에 맞는 학생 추가
		}
		
		//검색 결과 출력
		if(foundStudents.isEmpty()) {
			System.out.println("❌ '"+searchName+"'을(를) 포함한 학생을 찾을 수 없습니다.");
			System.out.println(" 정확한 이름이나 이름의 일부를 입력해보세요. ");
		} else {
			System.out.println("\n✅ 검색 결과: "+foundStudents.size()+"명");
			System.out.println("-".repeat(50));
			System.out.printf("%-10s %-5s %-15s %-5s\n","이름", "나이", "전공", "학점");
			System.out.println("-".repeat(50));
			
			//검색된 학생들만 출력 (foundStudents 리스트)
			for(Student student: foundStudents) {
				System.out.printf("%-10s %-5s %-15s %-5.1f\n",student.getName(), student.getAge(), student.getMajor(), student.getGpa());
			}
		}
		
	}

	private static void addStudents() {
		System.out.println("\n📝 새 학생 정보를 입력하세요.");
		System.out.println("-".repeat(25));
		
		//학생이름 입력
		System.out.print("이름 : ");
		String name = scanner.nextLine();
		
		//나이 입력 -- 유효성 검증 (while)-잘못 입력 시 해당 필드만 재입력
		int age;
		while(true) {//유효한 입력까지 반복
			System.out.print("나이 (0-150) : ");
			try {
				age = scanner.nextInt();	//정수 입력받기
				scanner.nextLine();			//버퍼 비우기
				
				if(age >= 0 && age <= 150) {
					break;	//유요한 값이면 루프 탈출
				} else {
					System.out.println("❌ 나이는 0 이상 150 이하여야 합니다. 다시 입력해주세요.");
				}
				
			} catch(Exception e) {
				System.out.println("❌ 숫자만 입력해주세요!");
				scanner.nextLine();		//잘못된 입력 버퍼 지우기
			}
		}
		
		//전공 입력
		System.out.print("전공 : ");
		String major = scanner.nextLine();
		
		//학점 입력
		double gpa;
		while(true) {
			System.out.print("학점 (0.0 ~ 4.0) : ");
			try {
				gpa = scanner.nextDouble();		//실수 입력받기
				scanner.nextLine();				//버퍼 지우기
				
				if(gpa >= 0.0 && gpa <= 4.0) {
					break;	//유요한 값이면 루프 탈출
				} else {
					System.out.println("❌ 학점은 0.0 이상 4.0 이하여야 합니다. 다시 입력해주세요.");
				}
				
			} catch (Exception e) {
				System.out.println("❌ 숫자만 입력해주세요!");
				scanner.nextLine();		//잘못된 입력 버퍼 지우기				
			}
		}
		
		//Student 객체 생성 및 ArrayList에 추가
		Student newStudent = new Student(name, age, major, gpa);
		//ArrayList의 add() 메서드 : 리스트 끝에 요소 추가
		students.add(newStudent);
		System.out.println("✅ 학생 '"+name+"'이(가) 성공적으로 추가되었습니다!");
		System.out.println("📊 현재 총 학생 수: "+students.size());
		
	}

	private static void viewAllStudents() {
		System.out.println("===== 전체 학생 목록 =====");
		System.out.println("이름     나이   전공        학점");
		System.out.println("--------------------------------------------");
		for(Student student : students) {
			System.out.printf("%-8s %-5d %-12s %5.1f\n",student.getName(), student.getAge(), student.getMajor(), student.getGpa());
			
		}
		System.out.println("\n총 "+students.size()+"명의 학생이 등록되어 있습니다.");
		
		//2. Iterator 사용
		System.out.println("\n[Iterator 사용]");
		Iterator<Student> itr = students.iterator();
		int count = 1;
		while(itr.hasNext()) {
			Student student = itr.next();
			System.out.println(count+". "+student.getName()+"("+
								student.getMajor()+")");
			count++;
		}
		
	}

	private static void printMenu() {
		System.out.println("\n==== 학생 관리 시스템 ====");
		System.out.println("1. 학생 추가");
		System.out.println("2. 전체 학생 조회");
		System.out.println("3. 학생 검색");
		System.out.println("4. 학생 정보 수정");
		System.out.println("5. 학생 삭제");
		System.out.println("6. 통계 정보");
		System.out.println("0. 종료 ");
		System.out.print("선택 : ");
		
	}

	private static void initializeData() {
		students.add(new Student("이순신", 20, "컴퓨터공학", 3.8));
		students.add(new Student("신사임당", 21, "경제학과", 3.9));
		students.add(new Student("류성룡", 19, "전자공학", 4.0));
		students.add(new Student("유관순", 22, "디자인", 4.0));
		students.add(new Student("손흥민", 20, "컴퓨터공학", 3.7));
		
		
	}
}
