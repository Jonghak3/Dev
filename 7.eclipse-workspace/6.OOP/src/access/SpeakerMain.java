package access;

public class SpeakerMain {

	public static void main(String[] args) {
		Speaker speaker = new Speaker(90);
		speaker.showVolume();
		
		speaker.volumeUp();
		speaker.showVolume();
		
		speaker.volumeUp();
		speaker.showVolume();
		
		//speaker.volume = 200;		//필드에 직접 접근 (private 접근 오류)
		speaker.showVolume();
	}
}
