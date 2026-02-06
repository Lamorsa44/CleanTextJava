package transformers;

public class RemoveHorizontalLines implements TextTransformer {

    @Override
    public String getPrettyName() {
        return "Remove horizontal lines";
    }

    @Override
    public String transform(String text) {
        return text.replaceAll("(?m)^-+$", "");
    }
}
