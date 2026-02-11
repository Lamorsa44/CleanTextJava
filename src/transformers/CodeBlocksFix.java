package transformers;

public record CodeBlocksFix() implements TextTransformer {

    @Override
    public String getPrettyName() {
        return "Code Blocks Fix";
    }

    @Override
    public String transform(String text) {
        return text.replaceAll("(?m)^(\\w+)\\s+^```", "``` $1");
    }
}
