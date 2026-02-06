package transformers;

public class RemoveLastParagraph implements TextTransformer {

    @Override
    public String getPrettyName() {
        return "Remove last paragraph";
    }

    @Override
    public String transform(String text) {
        if (text.lines().limit(2).count() < 2) return text;
        return text.substring(0, text.stripTrailing().lastIndexOf("\n\n"));
    }
}
