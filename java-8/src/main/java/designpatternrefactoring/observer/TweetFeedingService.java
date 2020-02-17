package designpatternrefactoring.observer;

public class TweetFeedingService {

    public static void main(String[] args) {
        Feed feed = new Feed();
        feed.registerObserver(new Jtbc());
        feed.registerObserver(new Sbs());

        // 인터페이스 구현 없이 옵저버 등록이 가능하다.
        // 하지만, 무조건 람다가 능사는 아니다. 복잡한 비즈니스 로직을 갖는다면 기존 클래스 구현 방식이 더 바람직 할 수 있다.
        feed.registerObserver((String tweet) -> {
            if (tweet != null && tweet.contains("Trump")) {
                System.out.println("SBS :: about America Tweet! : " + tweet);
            }
        });

        String tweetMessage = "Show me the money! - Trump";
        feed.notifyObservers(tweetMessage);
    }
}
