# CleanText

A simple Java command-line application to clean up and format text directly from your system clipboard.

## Features

- **Check Clipboard**: Display the current text stored in the clipboard.
- **Remove Horizontal Lines**: Removes lines that consist entirely of dashes (e.g., `---`).
- **Normalize Paragraph Spacing**: Ensures there is exactly one empty line between paragraphs, removing redundant white space.
- **Remove Last Paragraph**: Quickly deletes the final paragraph from the clipboard content.

## Technologies Used

- **Java**: Built using Java (utilizing Java 21+ features like implicitly declared classes).
- **Java AWT**: Used for system clipboard integration (`java.awt.datatransfer`).
- **Regular Expressions**: Used for text processing and pattern matching.

## How to Use

1. **Run the application**: Execute the `Main` class.
2. **Menu Options**:
   - `1`: **Check clipboard** - Prints the current clipboard content.
   - `2`: **Remove horizontal lines** - Cleans lines containing only dashes.
   - `3`: **Leave only one line between paragraphs** - Normalizes spacing between blocks of text.
   - `4`: **Remove last paragraph** - Deletes the last section of text.
   - `0`: **Exit** - Closes the application.
3. **Automatic Update**: When you select a cleaning option, the modified text is automatically copied back to your clipboard.

## Requirements

- Java Development Kit (JDK) 21 or higher.
