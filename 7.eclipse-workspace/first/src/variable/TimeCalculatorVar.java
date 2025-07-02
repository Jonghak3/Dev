package variable;

import java.text.NumberFormat;
import java.util.Locale;

public class TimeCalculatorVar {

	public static void main(String[] args) {
		
		System.out.println("=== 시간 계산기 ===");
		System.out.println();
		
		int totalSeconds = 7395;	//7395초
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.KOREA);
		
		// 초를 시, 분, 초 로 변환
		System.out.println("--- 시간 변환 ---");
		System.out.println("총 초 : " +numberFormat.format(totalSeconds)+ "초");
		
		// 1시간 = 60분 * 60초 = 3600초
		int hour = totalSeconds / 3600;
		int remainingSeconds = totalSeconds % 3600;	//나머지 초 계산
		int min = remainingSeconds / 60;	// 1분 = 60초
		int seconds = remainingSeconds % 60;// 나머지 초 계산
		
		System.out.println("변환 결과 : "+hour+"시간 "+min+"분 "+seconds+"초 ");
		System.out.println();
		
		int dateWorkingHour = 8;
		int workingDays = 22;
		int hourSalary = 15000;
		
		
		
		System.out.println("--- 근무시간 계산 ---");
		System.out.println("일일 근무시간: "+ numberFormat.format(dateWorkingHour)+"시간");
		System.out.println("월 근무일수: "+numberFormat.format(workingDays)+"일");
		System.out.println("시급: "+numberFormat.format(hourSalary)+"원");
		System.out.println();
		
		int monthWorkingHour = dateWorkingHour*workingDays;
		int dailyPay = dateWorkingHour*hourSalary;
		int monthlySalary = monthWorkingHour*hourSalary;
		System.out.println("월 총 근무시간: "+numberFormat.format(monthWorkingHour)+"시간");
		System.out.println("일급: "+numberFormat.format(dailyPay)+"원");
		System.out.println("월급: "+numberFormat.format(monthlySalary)+"원");
		System.out.println();
		
		int yearWorkingHour = monthWorkingHour*12;
		int salary = monthlySalary*12;
		int dateSecond = 24*60*60;
		int weekMin = 24*60*7;
		System.out.println("--- 추가 정보 ---");
		System.out.println("연간 총 근무시간: "+numberFormat.format(yearWorkingHour)+"시간");
		System.out.println("연봉: "+numberFormat.format(salary)+"원");
		System.out.println("하루는 총 "+numberFormat.format(dateSecond)+"초 입니다");
		System.out.println("일주일은 총 "+numberFormat.format(weekMin)+"분 입니다");
	}

}
