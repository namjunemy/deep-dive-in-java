package designpatternrefactoring.templatemethod;

public class OnlineBankingService {

    public static void main(String[] args) {
        Customer customer = new Customer(1L, "김신한");
        Database.addCustomers(customer);

        SinhanBanking sinhanBanking = new SinhanBanking();
        sinhanBanking.processCustomer(1L);

        KakaoBanking kakaoBanking = new KakaoBanking();
        kakaoBanking.processCustomer(1L);

        // 람다로 직접 전달하면 OnlineBanking을 상속받지 않고 다양한 동작 추가 가능
        // 템플릿 메소드 패턴의 자잘한 코드를 제거할 수 있음
        new LambdaOnlineBanking().processCustomer(1L, (Customer c) -> System.out.println("신한 당첨금 지급 : " + c.getName()));
        new LambdaOnlineBanking().processCustomer(1L, (Customer c) -> System.out.println("카카오 당첨금 지급 : " + c.getName()));
    }
}
