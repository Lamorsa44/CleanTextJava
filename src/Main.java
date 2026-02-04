import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;

void main() {
    String info = """
            1. Check clipboard
            2. Remove horizontal lines
            3. Leave only one line between paragraphs
            4. Remove last paragraph
            5. Remove list spacing
            6. Remove listing
            7. Remove listing from titles
            0. Exit
            """;
    System.out.println(info);

    System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
    System.out.println(System.getProperty("stdin.encoding"));
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    boolean running = true;

    while (running) {
        try {
            switch (IO.readln()) {
                case "0" -> running = false;
                case "1" -> System.out.println(getStringFromClipBoard(clipboard));
                case "2" -> removeHorizontalLines(clipboard);
                case "3" -> oneLineBetweenParagraphs(clipboard);
                case "4" -> removeLastParagraph(clipboard);
                case "5" -> removeListSpacing(clipboard);
                case "6" -> removeListing(clipboard);
                case "7" -> removeListingFromTitles(clipboard);
                default -> System.out.println(info);
            }
        } catch (UnsupportedFlavorException e) {
            System.out.println("Unsupported clipboard data flavor");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

private void removeListingFromTitles(Clipboard clipboard) throws IOException, UnsupportedFlavorException {
    String data = getStringFromClipBoard(clipboard)
            .replaceAll("(?m)(^#+)(\\s+\\d+\\.\\s+)", "$1 ")
            .replaceAll("(?m)(^#+)(\\s+-\\s+)", "$1 ");
    printAndCopy(clipboard, data);
}

private void removeListing(Clipboard clipboard) throws IOException, UnsupportedFlavorException {
    String data = getStringFromClipBoard(clipboard)
            .replaceAll("(?m)(^\\d+\\.\\s+)", "")
            .replaceAll("(?m)(^\\s*-\\s+)", "");
    printAndCopy(clipboard, data);
}

private void removeListSpacing(Clipboard clipboard) throws IOException, UnsupportedFlavorException {
    String data = getStringFromClipBoard(clipboard)
            .replaceAll("(?m)(^\\d+\\.\\s+.*)(\r?\n)+", "$1")
            .replaceAll("(?m)(^\\s*-\\s+.*)(\r?\n)+", "$1\n");
    printAndCopy(clipboard, data);
}

private void removeLastParagraph(Clipboard clipboard) throws IOException, UnsupportedFlavorException {
    String data = getStringFromClipBoard(clipboard);
    String substring = data.substring(0, data.stripTrailing().lastIndexOf("\n\n"));
    printAndCopy(clipboard, substring);
}

private void oneLineBetweenParagraphs(Clipboard clipboard) throws IOException, UnsupportedFlavorException {
    String data = getStringFromClipBoard(clipboard)
            .replaceAll("(\\r?\\n\\s*){2,}", "\n\n");
    printAndCopy(clipboard, data);
}


private void removeHorizontalLines(Clipboard clipboard) throws IOException, UnsupportedFlavorException {
    String data = getStringFromClipBoard(clipboard).replaceAll("(?m)^-+$", "");
    printAndCopy(clipboard, data);
}

private static String getStringFromClipBoard(Clipboard clipboard) throws UnsupportedFlavorException, IOException {
    return (String) clipboard.getData(DataFlavor.stringFlavor);
}

private static void printAndCopy(Clipboard clipboard, String data) {
    System.out.println(data);
    clipboard.setContents(new StringSelection(data), null);
}
