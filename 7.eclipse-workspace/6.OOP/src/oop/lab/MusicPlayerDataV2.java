package oop.lab;

public class MusicPlayerDataV2 {
	int volume = 0;
	boolean isOn = false;
	
	void Off() {
		isOn = false;
		System.out.println("음악 플레이어를 종료합니다.");
	}
	
	void Status() {
		System.out.println("음악 플레이어 상태 확인");
		if(isOn) {
			System.out.println("음악 플레이어 ON, 볼륨 : "+volume);
		} else {
			System.out.println("음악 플레이어 OFF");
		}
	}
	
	void VolDown() {
		volume--;
		System.out.println("음악 플레이어 볼륨 : "+ volume);
	}
	
	void VolUp() {
		volume++;
		System.out.println("음악 플레이어 볼륨 : "+ volume);
	}
	
	void On() {
		isOn = true;
		System.out.println("음악 플레이어를 시작합니다.");
	}
	
}
