package oop.lab;

public class MusicPlayerMain2 {

	public static void main(String[] args) {
		MusicPlayerData musicplayer = new MusicPlayerData();
		
		//음악 플레이어 켜기
		musicplayer.isOn = true;
		System.out.println("음악 플레이어를 시작합니다.");
		
		//볼륨 증가
		musicplayer.volume++;
		System.out.println("음악 플레이어 볼륨 : "+ musicplayer.volume);
		
		//볼륨 증가
		musicplayer.volume++;
		System.out.println("음악 플레이어 볼륨 : "+ musicplayer.volume);
		
		//볼륨 감소
		musicplayer.volume--;
		System.out.println("음악 플레이어 볼륨 : "+ musicplayer.volume);
		
		//음악 플레이어 상태
		System.out.println("음악 플레이어 상태 확인");
		if(musicplayer.isOn) {
			System.out.println("음악 플레이어 ON, 볼륨 : "+musicplayer.volume);
		} else {
			System.out.println("음악 플레이어 OFF");
		}
		
		//음악 플레이어 끄기
		musicplayer.isOn = false;
		System.out.println("음악 플레이어를 종료합니다.");
	}
}
