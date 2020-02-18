package designpatternrefactoring.chainofresponsibility;

public class HeaderTextProcessing extends ProcessingObject<String> {

    @Override
    protected String handleWork(String input) {
        return "From NJ : " + input;
    }
}
