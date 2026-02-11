### CleanText

A powerful Java command-line utility designed to clean and format text directly from your system clipboard. It leverages modern Java features to provide a flexible, extensible, and state-driven text transformation engine.

---

### Features

*   **Seamless Clipboard Integration**: Automatically retrieves text from your system clipboard, applies transformations, and copies the result back to the clipboard.
*   **Built-in Transformers**:
    *   **Code Block Trail**: Cleans up trailing characters or artifacts in code blocks.
    *   **Code Blocks Fix**: Fixes common formatting issues within code segments.
    *   **One Line Between Paragraphs**: Normalizes spacing by ensuring exactly one empty line between text blocks.
    *   **Remove Horizontal Lines**: Deletes lines consisting of horizontal separators (e.g., `---`).
    *   **Remove Last Paragraph**: Quickly removes the final block of text from the content.
    *   **Remove List Spacing**: Removes extra whitespace between list items for a tighter look.
    *   **Remove Listing**: Strips leading numbers (`1.`, `2.`) or bullets (`-`, `*`) from lists.
    *   **Remove Listing From Titles**: Specifically targets and removes list markers from lines that appear to be titles.
*   **Custom Transformers (Composite Pattern)**: Create your own transformation pipelines by chaining multiple built-in transformers into a single named command.
*   **Persistent Storage**: Custom transformers are saved to your local machine (typically in `%AppData%\CleanText\customTransformers` on Windows) and reloaded automatically on startup.
*   **State-Based CLI**: A clean command-line interface that switches between **Normal**, **Custom**, and **Create** modes to manage transformations effectively.

---

### Technologies Used

*   **Java 21+**: Utilizes advanced language features:
    *   **Sealed Interfaces**: Ensures type safety and exhaustive pattern matching for states and transformers.
    *   **Record Types**: Used for concise, immutable data carriers and transformer implementations.
    *   **Pattern Matching for Switch**: Simplifies complex conditional logic in the UI and controller.
*   **Java AWT**: Used for robust system clipboard interaction (`java.awt.datatransfer`).
*   **Reflection**: The application uses reflection to automatically discover all available `TextTransformer` implementations at runtime.

---

### How to Use

1.  **Run**: Execute the `Main.java` file using JDK 21 or higher.
2.  **Navigate Modes**:
    *   Type `custom` to enter **Custom Mode** where your saved chains are listed.
    *   Type `normal` to return to the list of basic built-in transformers.
    *   Type `create` while in Custom Mode to start building a new composite transformer.
3.  **Transform Text**:
    *   Press `1` to **Check clipboard** and see the current text without applying changes.
    *   Select any numbered option (e.g., `2`, `3`) to apply that specific transformation. The result is immediately printed to the console and copied back to your clipboard.
4.  **Exit**: Press `0` to close the application.

---

### Extensibility

The project is designed with the **Open/Closed Principle** in mind for text transformations. To add a new transformation:

1.  Implement the `TextTransformer` interface in a new class or record.
2.  Add your new class to the `permits` clause in `TextTransformer.java`.
3.  **That's it!** The `FileLoaderService` will automatically detect your new class via reflection and add it to the UI menu.

---

### Requirements

*   **Java Development Kit (JDK) 21** or higher.
*   **Operating System**: Currently configured for Windows (uses `AppData` for persistence).