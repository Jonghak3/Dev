package oop.lab;

public class MusicPlayerMain3 {

	public static void main(String[] args) {
		MusicPlayerData musicplayer = new MusicPlayerData();
		
		//음악 플레이어 켜기
		musicplayerOn(musicplayer);
		
		//볼륨 증가
		musicplayerVolUp(musicplayer);
	
		//볼륨 증가
		musicplayerVolUp(musicplayer);
		
		//볼륨 감소
		musicplayerVolDown(musicplayer);
	
		//음악 플레이어 상태
		musicplayerStatus(musicplayer);
		
		//음악 플레이어 끄기
		musicplayerOff(musicplayer);

	}

	static void musicplayerOff(MusicPlayerData musicplayer) {
		musicplayer.isOn = false;
		System.out.println("음악 플레이어를 종료합니다.");
	}

	static void musicplayerStatus(MusicPlayerData musicplayer) {
		System.out.println("음악 플레이어 상태 확인");
		if(musicplayer.isOn) {
			System.out.println("음악 플레이어 ON, 볼륨 : "+musicplayer.volume);
		} else {
			System.out.println("음악 플레이어 OFF");
		}
		
	}

	static void musicplayerVolDown(MusicPlayerData musicplayer) {
		musicplayer.volume--;
		System.out.println("음악 플레이어 볼륨 : "+ musicplayer.volume);
		
	}

	static void musicplayerVolUp(MusicPlayerData musicplayer) {
		musicplayer.volume++;
		System.out.println("음악 플레이어 볼륨 : "+ musicplayer.volume);
	}

	static void musicplayerOn(MusicPlayerData musicplayer) {
		musicplayer.isOn = true;
		System.out.println("음악 플레이어를 시작합니다.");
	}
}
