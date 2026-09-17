package transformers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record CodeBlockTrail() implements TextTransformer {

    @Override
    public String getPrettyName() {
        return "Code block trail";
    }

    @Override
    public String transform(String text) {
        String s = Pattern.compile("(?s)(?m)^```(.*?)```").matcher(text)
                .replaceAll(matchResult -> Matcher.quoteReplacement("```%s\n```".formatted(matchResult.group(1).stripTrailing())));
        return s.replaceAll("(?m)^", "> ");
    }
}
