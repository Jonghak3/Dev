package condition;
/*
 * if-else if-else 조건문
 * 학생 학년 등급에 따른 쿠푠 발급 (금액)
 */
public class Switch1 {

	public static void main(String[] args) {
		int grade = 2;
		int coupon;
		if(grade == 1)
			coupon = 1000;
		else if(grade ==2)
			coupon = 2000;
		else if(grade == 3)
			coupon = 3000;
		else
			coupon = 500;
		
		System.out.println("발급받은 쿠폰 : " + coupon);
	}
}
