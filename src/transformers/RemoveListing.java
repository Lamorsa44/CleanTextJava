package transformers;

public class RemoveListing implements TextTransformer {

    @Override
    public String getPrettyName() {
        return "Remove listing";
    }

    @Override
    public String transform(String text) {
        return text
            .replaceAll("(?m)(^\\d+\\.\\s+)", "")
            .replaceAll("(?m)(^\\s*-\\s+)", "");
    }
}
