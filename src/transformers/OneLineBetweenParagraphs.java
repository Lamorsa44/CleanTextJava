package transformers;

public record OneLineBetweenParagraphs() implements TextTransformer {

    @Override
    public String getPrettyName() {
        return "Leave only one line between paragraphs";
    }

    @Override
    public String transform(String text) {
        return text.replaceAll("(\\r?\\n\\s*){2,}", "\n\n");
    }
}
