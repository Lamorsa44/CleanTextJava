import controllers.UIController;
import ui.CommandLineUI;

void main() {
    UIController controller = new UIController(new CommandLineUI());
    printCharset();
    controller.run();
    Runtime.getRuntime().addShutdownHook(new Thread(controller::exit));
}

private static void printCharset() {
    System.out.printf("In-encoding: %s\n", System.getProperty("stdin.encoding"));
    System.out.printf("Out-encoding: %s\n", System.getProperty("stdout.encoding"));
}
