package transformers;

public record RemoveLastParagraph() implements TextTransformer {

    @Override
    public String getPrettyName() {
        return "Remove last paragraph";
    }

    @Override
    public String transform(String text) {
        String stripped = text.stripTrailing();
        int lastIndex = stripped.lastIndexOf("\n\n");
        if (lastIndex == -1) return text;
        return text.substring(0, lastIndex);
    }
}
