import controllers.UIController;
import ui.CommandLineUI;

void main() {
    CommandLineUI ui = new CommandLineUI();
    UIController controller = new UIController(ui);
    printCharset();
    controller.run();
}

private static void printCharset() {
    System.out.printf("In-encoding: %s\n", System.getProperty("stdin.encoding"));
    System.out.printf("Out-encoding: %s\n", System.getProperty("stdout.encoding"));
}
