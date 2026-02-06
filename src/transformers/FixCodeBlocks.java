package transformers;

public class FixCodeBlocks implements TextTransformer {

    @Override
    public String getPrettyName() {
        return "Fix code blocks";
    }

    @Override
    public String transform(String text) {
        return text.replaceAll("(?m)^(\\w+)\\s+^```", "``` $1");
    }
}
