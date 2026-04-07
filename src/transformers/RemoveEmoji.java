package transformers;

/**
 * Transformer that removes emojis (non-ASCII) and any whitespace following them.
 */
public record RemoveEmoji() implements TextTransformer {

    @Override
    public String getPrettyName() {
        return "Remove emojis";
    }

    @Override
    public String transform(String text) {
        if (text == null) return null;
        // Matches non-ASCII emojis followed by zero or more whitespace characters
        return text.replaceAll("[\\p{IsEmoji}&&[^\\p{IsASCII}]]\\s*", "");
    }
}
