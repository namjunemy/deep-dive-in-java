package designpatternrefactoring.templatemethod;

public class SinhanBanking extends OnlineBanking {

    @Override
    void makeCustomerHappy(Customer c) {
        System.out.println("신한 이자 지급 대상자 : " + c.getName());
    }
}
