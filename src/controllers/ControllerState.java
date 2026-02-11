package controllers;

import services.ClipboardService;
import transformers.TextTransformer;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;

public sealed interface ControllerState permits CreateState, CustomState, NormalState {

    UIController getUIController();

    void handleInput(String input) throws IOException, UnsupportedFlavorException;

    default void transformAndCopy(String input) throws IOException, UnsupportedFlavorException {
        final ClipboardService clipboard = getUIController().getClipboard();

        switch (this) {
            case NormalState _ -> clipboard.copy(getUIController()
                    .getTransformers()
                    .get(Integer.parseInt(input) - 2)
                    .transform(clipboard.getString())).printString();
            case CustomState _ -> clipboard.copy(getUIController()
                    .getCustomTransformers()
                    .get(Integer.parseInt(input) - 2)
                    .transform(clipboard.getString())).printString();
            case CreateState _ -> throw new UnsupportedOperationException("CreateState does not support transformation");
        }
    }

    default boolean intInRange(String s, int initialValue, List<TextTransformer> transformers) {
        return Integer.parseInt(s) - initialValue < transformers.size();
    }
}
