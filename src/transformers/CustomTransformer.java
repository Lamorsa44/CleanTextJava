package transformers;

import java.util.List;

public record CustomTransformer(String prettyName, List<TextTransformer> transformers)
    implements TextTransformer {

    @Override
    public String getPrettyName() {
        return prettyName;
    }

    @Override
    public String transform(String text) {
        return transformers.stream()
                .reduce(text, (currentText, transformer) -> transformer.transform(currentText), (_, text2) -> text2);
    }
}
