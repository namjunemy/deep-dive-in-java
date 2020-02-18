package designpatternrefactoring.chainofresponsibility;

public class SpellCheckerProcessing extends ProcessingObject<String> {

    @Override
    protected String handleWork(String input) {
        return input.replace("labda", "lambda");
    }
}
