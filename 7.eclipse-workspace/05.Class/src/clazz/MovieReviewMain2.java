package clazz;

public class MovieReviewMain2 {

	public static void main(String[] args) {
		MovieReview kpopDemon = new MovieReview();
		kpopDemon.title = "케이팝 데몬 헌터스";
		kpopDemon.review = "신나는 분위기";
		
		MovieReview yadang = new MovieReview();
		yadang.title = "야당";
		yadang.review = "연기 뛰어나고 재미있음";
		
		MovieReview[] list = new MovieReview[2];
		list[0] = kpopDemon;
		list[1] = yadang;
		
//		for(int i = 0; i < list.length;i++ ) {
//			System.out.println("영화 리뷰 "+(i+1)+"번 - 영화 제목: "+list[i].title+" 리뷰 : "+list[i].review);
//		}
		
		for (MovieReview review : list) {
			System.out.println("영화 제목: "+review.title+" 리뷰 : "+review.review);
		}
		
	}
}
