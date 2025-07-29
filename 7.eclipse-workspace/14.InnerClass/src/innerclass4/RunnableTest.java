package innerclass4;



public class RunnableTest {

	//1. Runnable 인터페이스 구현 클래스
	static class NamedRunnable implements Runnable {

		@Override
		public void run() {
			System.out.println("🧑‍🦲 NamedRunnable 실행 중입니다.");
		}
		
	}
	
	public static void main(String[] args) {
		
		//방식 1. : 일반 클래스 방식 (이름이 있는 클래스)
		Runnable named = new NamedRunnable();
		named.run();
		
		//방식 2. : 익명 내부 클래스 방식
		Runnable anonymous = new Runnable() {
			
			@Override
			public void run() {
				System.out.println("👻 익명 Runnable: 실행 중입니다.");
				
			}
		};
		
		anonymous.run();
		
		//3.Thread와 함께 사용하기
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("🚀 Thread 익명 Runnable 실행!");
				
				
			}
		});
		thread.start();
		
		try {
			Thread.sleep(5000);	//0.5초 대기 (main 메서드 끝날 때까지 기다리기 위한 sleep)
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


