package transformers;

import java.util.regex.Pattern;

public record CodeBlockTrail() implements TextTransformer {

    @Override
    public String getPrettyName() {
        return "Code block trail";
    }

    @Override
    public String transform(String text) {
        return Pattern.compile("(?s)(?m)^```(.*?)```").matcher(text)
                .replaceAll(matchResult -> "```%s\n```".formatted(matchResult.group(1).stripTrailing()));
    }
}
