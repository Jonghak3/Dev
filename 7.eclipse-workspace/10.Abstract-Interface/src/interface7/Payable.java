package interface7;
/*
 * - 결제 가능한 객체를 나타내는 기본 인터페이스 
 * - 결제 시스템의 최상위 인터페이스로 사용함
 */
public interface Payable {
	
	//결제 처리 추상 메서드 - 각 결제 수단별 고유한 결제 로직 구현함 
	boolean processPayment(double amount);
	
	//결제 수수료 계산하는 디폴트 메서드
	//기본 정책 : 결제 금액의 2% 수수료 계산
	default double calculateFee(double amount) {
		return amount*0.02;
	}
	
	default String getPaymentMethod() {
		return "일반 결제";
	}
}
