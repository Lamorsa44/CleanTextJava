package controllers;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public final class CustomState implements ControllerState {

    private final UIController uiController;

    public CustomState(UIController uiController) {
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
            case String s when s.matches("^\\d+$") && intInRange(s, 2, uiController.getTransformers()) ->
                    transformAndCopy(s);
            case "create" -> uiController.setState(new CreateState(uiController));
            case "normal" -> uiController.setState(new NormalState(uiController));
            default -> uiController.getUi().printInfo(uiController);
        }
    }
}
