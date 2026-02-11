package services;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ClipboardService {

    private static final LazyConstant<ClipboardService> lazyInstance = LazyConstant
            .of(() -> new ClipboardService(Toolkit.getDefaultToolkit().getSystemClipboard()));
    private final Clipboard clipboard;

    private ClipboardService(Clipboard clipboard) {
        this.clipboard = clipboard;
    }

    public static ClipboardService getInstance() {
        return lazyInstance.get();
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
