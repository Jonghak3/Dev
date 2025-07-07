package loop;

public class NestedLoopLab {

	public static void main(String[] args) {
		System.out.println("Lab1. 시간표 생성 시스템");
		System.out.println("9시부터 18시까지, 월요일부터 금요일까지 시간표 생성하기");
		System.out.println();
		
		//요일 배열
		String[] days = {"월", "화", "수", "목", "금"};
		
		System.out.printf("%-8s", "시간\\요일");	//첫 번째 셀
		for(int day = 0; day < days.length; day++) {
			System.out.printf("%-8s", days[day]);
		}
		System.out.println(); 	//개행
		
		//시간표 출력하기 
		for(int hour = 9; hour < 19; hour++) {
			System.out.printf("%-8s", hour + "시");
			for(int day = 0; day < days.length; day++) {
				if((hour+day)%3 == 0) {
					System.out.printf("%-8s", "회의");
				} else {
					System.out.printf("%-8s", "자유");
				}
			}
			System.out.println();
		}
		
		System.out.println("\n"+"=".repeat(50)+"\n");
		
		// lab2. 좌석 예약 현황
		seatReservation();
		
	}

	private static void seatReservation() {
		System.out.println("Lab2. 좌석 예약 현황 시스템");
		System.out.println("8행 10열의 좌석 현황을 표시합니다. (O: 예약가능, X: 예약불가)");
		System.out.println();
		
		//좌석 배치 정보
		int rows = 8;
		int cols = 10;
		
		// 열 번호 헤더 출력
		System.out.print("   ");
		for(int col = 1; col <= cols; col++ ) {
			System.out.print(col+" ");		// 각 열번호를 공배으로 구분
		}
		System.out.println();	// 헤더 줄 끝에 개행
		
		// 좌석 예약 현황 출력
		for(int row = 1; row <= rows; row++) {
			System.out.print(row+": ");		//행 번호 출력
			for(int col = 1; col <= cols; col++) {
				//예약 상태 시뮬레이션 -- (행번호 + 열번호)가 3의 배수인 위치 => 예약완료(X)
				if((row+col)%3==0) {
					System.out.print("X ");	//예약 완료 표시
				} else {
					System.out.print("O ");	//예약 가능 표시
				}
			}
			System.out.println();	// 각 행 끝에 개행
		}
		System.out.println();
		System.out.println();
		System.out.println("총 좌석 수: "+rows*cols);
		
		int reservedSeats = 0;		//예약된 좌석 수
		
		//예약된 좌석 수 계산
		for(int col = 1; col<= cols; col++) {	//모든 행 반복
			for(int row = 1; row<= rows; row++) {	// 모든 열 반복
				if((col+row)%3 ==0) {
					reservedSeats += 1;		// 예약된 좌석 수 증가
				}
			}
		}
		System.out.println("예약된 좌석: "+reservedSeats);
		System.out.println("예약 가능: "+ (rows*cols-reservedSeats));
		
		
	}
}
