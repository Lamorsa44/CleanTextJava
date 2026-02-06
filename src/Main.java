import transformers.*;

import java.awt.*;
import java.awt.datatransfer.*;
import java.util.List;

void main() {
    ClipboardService clipboardService = new ClipboardService(Toolkit.getDefaultToolkit().getSystemClipboard());
    var transformers = List.of(
            new OneLineBetweenParagraphs(),
            new RemoveListingFromTitles(),
            new RemoveHorizontalLines(),
            new RemoveListSpacing(),
            new RemoveLastParagraph(),
            new RemoveListing(),
            new FixCodeBlocks()
    );

    String info = getInfo(transformers);
    System.out.println(info);

    printCharset();
    boolean running = true;

    while (running) {
        try {
            switch (IO.readln()) {
                case "0" -> running = false;
                case "1" -> clipboardService.printString();
                case String s when s.matches("^\\d+$") && Integer.parseInt(s) - 2 < transformers.size() ->
                        clipboardService.copy(
                                transformers.get(Integer.parseInt(s) - 2).transform(clipboardService.getString())).printString();
                default -> System.out.println(info);
            }
        } catch (UnsupportedFlavorException e) {
            System.out.println("Unsupported clipboard data flavor");
        } catch (IOException e) {
            System.out.printf("Error reading clipboard: %s\n", e.getMessage());
        }
    }
}

private static String getInfo(List<TextTransformer> transformers) {
    StringBuilder stringBuilder = new StringBuilder();
    AtomicInteger counter = new AtomicInteger(2);
    stringBuilder.append("1. Check clipboard\n");
    transformers.forEach(transformer -> stringBuilder
            .append("%d. %s\n".formatted(counter.getAndIncrement(), transformer.getPrettyName())));
    stringBuilder.append("0. Exit");

    return stringBuilder.toString();
}

private static void printCharset() {
    System.out.printf("In-encoding: %s\n", System.getProperty("stdin.encoding"));
    System.out.printf("Out-encoding: %s\n", System.getProperty("stdout.encoding"));
}
