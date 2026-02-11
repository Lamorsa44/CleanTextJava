package controllers;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public final class NormalState implements ControllerState {

    private final UIController uiController;

    public NormalState(UIController uiController) {
        this.uiController = uiController;
    }

    @Override
    public UIController getUIController() {
        return uiController;
    }

    @Override
    public void handleInput(String input) throws IOException, UnsupportedFlavorException {
        switch (input) {
            case "0" -> uiController.exit();
            case "1" -> uiController.getClipboard().printString();
            case String s when s.matches("^\\d+$") &&
                    Integer.parseInt(s) - 2 < uiController.getTransformers().size() ->
                transformAndCopy(s);
            case "custom" -> uiController.setState(new CustomState(uiController));
            default -> uiController.getUi().printInfo(uiController);
        }
    }
}
