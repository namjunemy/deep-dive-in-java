package designpatternrefactoring.templatemethod;

public class KakaoBanking extends OnlineBanking {

    @Override
    void makeCustomerHappy(Customer c) {
        System.out.println("카카오 이자 지급 대상자 : " + c.getName());
    }
}
