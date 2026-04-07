package controllers;

import services.FileLoaderService;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public record DeleteState(UIController uiController) implements ControllerState {

    @Override
    public UIController getUIController() {
        return uiController;
    }

    @Override
    public void handleInput(String input) throws IOException, UnsupportedFlavorException {
        switch (input) {
            case String s when s.matches("^\\d+$") &&
                    intInRange(s, 0, uiController.getTransformers()) -> deleteTransformer(s);
            case "back" -> uiController.setState(new CustomState(uiController));
            default -> uiController.getUi().printInfo(uiController);
        }
    }

    public void deleteTransformer(String s) {
        try {
            var removed = uiController.getCustomTransformers().get(Integer.parseInt(s));
            FileLoaderService.deleteTransformer(removed);
            uiController.getCustomTransformers().remove(removed);
            uiController.getUi().printInfo(uiController);
        } catch (IOException e) {
            uiController.getUi().printError("Could not delete transformer: " + e.getMessage());
        }
    }
}
