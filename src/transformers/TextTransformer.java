package transformers;

public interface TextTransformer {

    String getPrettyName();
    String transform(String text);
}
