package designpatternrefactoring.chainofresponsibility;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;
import java.util.function.UnaryOperator;

@Slf4j
public class ChainService {

    public static void main(String[] args) {
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();

        p1.setSuccessor(p2);

        String result = p1.handle("Aren't labda really sexy?!");
        log.info(result);

        replaceLambda();
    }

    private static void replaceLambda() {
        UnaryOperator<String> headerProcessing = (String text) -> "From NJ : " + text;
        UnaryOperator<String> spellCheckerProcessing = (String text) -> text.replace("labda", "lambda");

        Function<String, String> pipelines = headerProcessing.andThen(spellCheckerProcessing);
        String result = pipelines.apply("Aren't labda really sexy?!");

        log.info("use lambda result > {}", result);
    }
}
