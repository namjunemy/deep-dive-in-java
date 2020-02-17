package designpatternrefactoring.observer;

public class Sbs implements Observer {

    @Override
    public void notify(String tweet) {
        if (tweet != null && tweet.contains("entertainment")) {
            System.out.println("SBS :: about Entertainment Tweet! : " + tweet);
        }
    }
}
