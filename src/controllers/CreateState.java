package controllers;

import services.FileLoaderService;
import transformers.CustomTransformer;
import transformers.TextTransformer;

import java.util.ArrayList;
import java.util.List;

public final class CreateState implements ControllerState {

    private final UIController uiController;
    private final String transformerName;
    private final List<TextTransformer> selectedTransformers = new ArrayList<>();

    public CreateState(UIController uiController) {
        this.uiController = uiController;
        transformerName = uiController.getUi().requestCustomTransformerName();
    }

    @Override
    public UIController getUIController() {
        return uiController;
    }

    @Override
    public void handleInput(String input) {
            switch (input) {
                case "0" -> uiController.setState(new CustomState(uiController));
                case "1" -> saveCustomTransformer(transformerName, selectedTransformers);
                case String s when s.matches("^\\d+$") && intInRange(s, 2, uiController.getTransformers()) ->
                        addTransformer(s, selectedTransformers, uiController.getTransformers());
                default -> uiController.getUi().printInfo(uiController);
            }
    }

    private void addTransformer(String s, List<TextTransformer> selectedTransformers, List<TextTransformer> transformers) {
        selectedTransformers.add(transformers.get(Integer.parseInt(s) - 2));
        uiController.getUi().printInfo(uiController);
    }

    private void saveCustomTransformer(String transformerName, List<TextTransformer> selectedTransformers) {
        if (selectedTransformers.isEmpty() || transformerName.isBlank()) return;

        CustomTransformer customTransformer = new CustomTransformer(transformerName, selectedTransformers);
        FileLoaderService.saveCustomTransformer(customTransformer);
        uiController.getCustomTransformers().add(customTransformer);
        uiController.setState(new CustomState(uiController));
    }

    public List<TextTransformer> getSelectedTransformers() {
        return selectedTransformers;
    }
}
