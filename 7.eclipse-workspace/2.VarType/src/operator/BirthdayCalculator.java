package operator;

public class BirthdayCalculator {

	public static void main(String[] args) {
		// 현재 날짜 정보
		int currentYear = 2025;
		int currentMonth = 7;
		int currentDay = 2;
		
		// 생일 정보
		int birthMonth = 11;
		int birthDay = 25;
		
		// 정보 출력
		System.out.println("=== 생일 정보 ===");
		System.out.println("현재 날짜 : "+currentYear+"년 "+currentMonth+"월 "+currentDay+"일 ");
		System.out.println("생일 : " + birthMonth+"월 "+birthDay+"일 ");
		
		if (currentMonth == birthMonth) {
			System.out.println("이번 달이 생일달입니다!");
			if(currentDay == birthDay) {
				System.out.println("오늘이 생일입니다! 축하합니다! 🎉");
			} else if(currentDay < birthDay) {
				System.out.println("생일이 이번 달에 있습니다.");
			} else {
				System.out.println("이번 달 생일이 지났습니다.");
			}
		} else if (currentMonth < birthMonth) {
			System.out.println("생일이 올해 안에 있습니다.");
		} else {
			System.out.println("올해 생일이 지났습니다. 내년을 기다리세요!");
		}
	}
}
