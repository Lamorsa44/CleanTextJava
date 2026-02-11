package transformers;

public record RemoveListSpacing() implements TextTransformer {

    @Override
    public String getPrettyName() {
        return "Remove list spacing";
    }

    @Override
    public String transform(String text) {
        return text
            .replaceAll("(?m)(^\\d+\\.\\s+.+)(\\s*\r?\n){2,}(?=^\\d+\\.\\s+.+)", "$1\n")
            .replaceAll("(?m)(^\\s*-\\s+.+)(\\s*\r?\n){2,}(?=^\\s*-\\s+.+)", "$1\n");
    }
}
