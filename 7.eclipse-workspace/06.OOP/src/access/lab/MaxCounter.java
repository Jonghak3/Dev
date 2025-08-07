package access.lab;

public class MaxCounter {
	private int count = 0;
	private int max;	// 최대 허용 카운터 값을 저장하는 필드

	public MaxCounter(int max) {	//매개변수로 받은 max값을 인스턴스 변수에 저장
		//super();
		this.max = max;
	}
	
	
	void increment() {
		if(count >= max) {
			System.out.println("최대값을 초과할 수 없습니다.");
		} else {
			count++;
		}
	}
	
	int getCount() {
		return count;
	}
	
	
}
