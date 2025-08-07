package access.lab;

/*
 * (출력)
 * 		최대값을 초과할 수 없습니다.
 * 
 */
public class MaxCounterMain {

	public static void main(String[] args) {
		MaxCounter counter = new MaxCounter(3);	//최대값 3으로 설정
		//
		counter.increment();	//count = 1
		counter.increment();	//count = 2
		counter.increment();	//count = 3
		
		counter.increment();	//최대값 초과 메시지 출력
		int count = counter.getCount();
		System.out.println(count);
		
	}
}
