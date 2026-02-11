package ui;

import controllers.CreateState;
import controllers.CustomState;
import controllers.NormalState;
import controllers.UIController;
import services.FileLoaderService;
import transformers.TextTransformer;

import java.util.List;
import java.util.Scanner;

public class CommandLineUI implements CleanTextUI {

    private final Scanner scanner = new Scanner(System.in);
    private final String normalInfo = buildInfoWTransformers(FileLoaderService.getTextTransformers())
            .append("custom. Custom Mode").toString();

    @Override
    public void printTransformers() {
        System.out.println(normalInfo);
    }

    @Override
    public void printCustomTransformers(UIController controller) {
        System.out.println(buildInfoWTransformers(controller.getCustomTransformers())
                .append("create. Create custom transformer\n")
                .append("normal. Normal Mode\n")
        );
    }

    @Override
    public String readInput() {
        return scanner.nextLine();
    }

    @Override
    public void printInfo(UIController controller) {
        switch (controller.getState()) {
            case NormalState _ -> printTransformers();
            case CustomState _ -> printCustomTransformers(controller);
            case CreateState createState -> {
                printCustomTransformerCreation(createState.getUIController().getTransformers());
                printSelectedTransformers(createState.getSelectedTransformers());
            }
        }
    }

    @Override
    public String requestCustomTransformerName() {
        System.out.println("Enter custom transformer name:");
        return readInput();
    }

    @Override
    public void printCustomTransformerCreation(List<TextTransformer> transformers) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("1: Save\n");
        appendTransformers(transformers, stringBuilder, 2);
        stringBuilder.append("0: Return without saving\n");
        System.out.println(stringBuilder);
    }

    @Override
    public void printSelectedTransformers(List<TextTransformer> selectedTransformers) {
        System.out.println(selectedTransformers.stream()
                .map(TextTransformer::getPrettyName)
                .reduce((text1, text2) -> text1 + " -> " + text2).orElse(""));
    }
}
