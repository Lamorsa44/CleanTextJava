import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;

void main() {
    System.out.println("""
            1. Check clipboard
            2. Remove horizontal lines
            3. Leave only one line between paragraphs
            4. Remove last paragraph
            0. Exit
            """);

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
            }
        } catch (UnsupportedFlavorException e) {
            System.out.println("Unsupported clipboard data flavor");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

private void removeLastParagraph(Clipboard clipboard) throws IOException, UnsupportedFlavorException {
  String data = getStringFromClipBoard(clipboard);
  String substring = data.substring(0, data.stripTrailing().lastIndexOf("\n\n"));
  System.out.println(substring);
  clipboard.setContents(new StringSelection(substring), null);
}

private void oneLineBetweenParagraphs(Clipboard clipboard) throws IOException, UnsupportedFlavorException {
    String data = getStringFromClipBoard(clipboard)
            .replaceAll("(\\r?\\n\\s*){2,}", "\n\n");
    System.out.println(data);
    clipboard.setContents(new StringSelection(data), null);
}


private void removeHorizontalLines(Clipboard clipboard) throws IOException, UnsupportedFlavorException {
  String data = getStringFromClipBoard(clipboard).replaceAll("(?m)^-+$", "");
  System.out.println(data);
  clipboard.setContents(new StringSelection(data), null);
}

private static String getStringFromClipBoard(Clipboard clipboard) throws UnsupportedFlavorException, IOException {
  return (String) clipboard.getData(DataFlavor.stringFlavor);
}
