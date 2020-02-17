package designpatternrefactoring.observer;

public class Jtbc implements Observer {

    @Override
    public void notify(String tweet) {
        if (tweet != null && tweet.contains("money")) {
            System.out.println("JTBC :: about Money Tweet! : " + tweet);
        }
    }
}
