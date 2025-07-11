package clazz;

public class MovieReviewMain {

	public static void main(String[] args) {
		MovieReview kpopDemon = new MovieReview();
		kpopDemon.title = "케이팝 데몬 헌터스";
		kpopDemon.review = "신나는 분위기";
		
		MovieReview yadang = new MovieReview();
		yadang.title = "야당";
		yadang.review = "연기 뛰어나고 재미있음";
		
		System.out.println("영화 제목: "+kpopDemon.title+", 리뷰: "+kpopDemon.review);
		System.out.println("영화 제목: "+yadang.title+", 리뷰: "+yadang.review);
		
	}
}
