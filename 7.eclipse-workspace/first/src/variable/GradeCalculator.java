package variable;

public class GradeCalculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("=== 성적 계산기 ===");
		System.out.println("학생명: 신사임당");
		System.out.println("학번: 2025001");
		System.out.println();
		System.out.println("--- 과목별 점수 ---");
		int math = 85, english = 92, science = 78, korean = 88, history = 90;
		System.out.println("수학: "+math+"점");
		System.out.println("영어: "+english+"점");
		System.out.println("과학: "+science+"점");
		System.out.println("국어: "+korean+"점");
		System.out.println("역사: "+history+"점");
		System.out.println();
		int total = math+english+science+korean+history;
		int average = total/5;
		int max = math;
		int minimum = math;
		if (english > max) {
			max = english;
		};
		if (science > max) {
			max = science;
		};
		if (korean > max) {
			max = korean;
		};
		if (history > max) {
			max = history;
		}
		if (english < minimum) {
			minimum = english;
		};
		if (science < minimum) {
			minimum = science;
		};
		if (korean < minimum) {
			minimum = korean;
		};
		if (history < minimum) {
			minimum = history;
		}
		int gap = max - minimum;
		System.out.println("총점: "+total+"점");
		System.out.println("평균: "+average+"점");
		System.out.println("최고점: "+max+"점");
		System.out.println("최저점: "+minimum+"점");
		System.out.println("최고 최저 점수 차이: "+gap+"점");
		System.out.println();
		String grade;
		if(average>89) {
			grade = "A(우수)";
		} else if (average>79) {
			grade = "B(양호)";
		} else if (average>69) {
			grade = "C(별로)";
		} else if (average>59) {
			grade = "D(썩)";
		} else {
			grade = "F(fuck)";
		}
		
		
		System.out.println("--- 등급 판정 ---");
		System.out.println("등급: "+grade);

	}

}
