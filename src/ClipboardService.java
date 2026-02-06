import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ClipboardService {

    private final Clipboard clipboard;

    public ClipboardService(Clipboard clipboard) {
        this.clipboard = clipboard;
    }

    public ClipboardService copy(String string) {
        clipboard.setContents(new StringSelection(string), null);
        return this;
    }

    public void printString() throws IOException, UnsupportedFlavorException {
        System.out.println(getString());
    }

    public String getString() throws UnsupportedFlavorException, IOException {
        return clipboard.getContents(null).getTransferData(DataFlavor.stringFlavor).toString();
    }
}
