package ui;

import controllers.UIController;
import transformers.TextTransformer;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/*Responsible for user interaction
* Should accept String as info and List of Transformers to display them*/
public interface CleanTextUI {

    String readInput();
    void printInfo(UIController controller);
    void printTransformers();
    void printCustomTransformers(UIController controller);
    String requestCustomTransformerName();
    void printCustomTransformerCreation(List<TextTransformer> transformers);
    void printSelectedTransformers(List<TextTransformer> selectedTransformers);

    default StringBuilder buildInfoWTransformers(List<? extends TextTransformer> transformers) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("1. Check clipboard\n");
        appendTransformers(transformers, stringBuilder, 2);
        stringBuilder.append("0. Exit\n");
        return stringBuilder;
    }

    default void appendTransformers(List<? extends TextTransformer> transformers, StringBuilder stringBuilder, int initialValue) {
        AtomicInteger counter = new AtomicInteger(initialValue);
        transformers.forEach(transformer -> stringBuilder
                .append("%d. %s\n".formatted(counter.getAndIncrement(), transformer.getPrettyName())));
    }
}
