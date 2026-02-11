package controllers;

import services.ClipboardService;
import services.FileLoaderService;
import transformers.CustomTransformer;
import transformers.TextTransformer;
import ui.CleanTextUI;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UIController {

    private final ClipboardService clipboardService = ClipboardService.getInstance();
    private final CleanTextUI ui;
    private final List<TextTransformer> transformers;
    private final List<CustomTransformer> customTransformers;
    private ControllerState state;
    private boolean running;

    public UIController(CleanTextUI ui) {
        this.ui = ui;
        transformers = FileLoaderService.getTextTransformers();
        customTransformers = new ArrayList<>(FileLoaderService.getCustomTransformers());
        state = new CustomState(this);
        running = true;
    }

    public void exit() {
        running = false;
    }

    public void run() {
        ui.printInfo(this);

        while (running) {
            try {
                state.handleInput(ui.readInput());
            } catch (UnsupportedFlavorException e) {
                System.out.println("Unsupported clipboard data flavor");
            } catch (IOException e) {
                System.out.printf("Error reading clipboard: %s\n", e.getMessage());
            }
        }
    }

    public ClipboardService getClipboard() {
        return clipboardService;
    }

    public CleanTextUI getUi() {
        return ui;
    }

    public List<TextTransformer> getTransformers() {
        return transformers;
    }

    public List<CustomTransformer> getCustomTransformers() {
        return customTransformers;
    }

    public ControllerState getState() {
        return state;
    }

    public void setState(ControllerState controllerState) {
        System.out.println("Changing states");
        state = controllerState;
        ui.printInfo(this);
    }
}
