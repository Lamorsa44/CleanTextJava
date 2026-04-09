package transformers;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public record WrapCodeBlockInCallout() implements TextTransformer {

    @Override
    public String getPrettyName() {
        return "Wrap code block in code callout";
    }

public String transform(String text) {
    Flag flag = new Flag();
    var pattern = Pattern.compile("(?s)(?m)(?<!\\[!code-?].*%n)^```(.*?)```");
    return pattern.matcher(text).replaceAll(matchResult -> {
        String header = "[!code%s]\n".formatted(flag.toggle() ? "-" : "");
        String combined = header + matchResult.group();
        return combined.replaceAll("(?m)^", "> ");
    });
}

    record Flag(AtomicBoolean bool) {
        public Flag() {
            this(new AtomicBoolean(false));
        }

        public boolean toggle() {
            boolean current = bool.get();
            bool.set(!current);
            return current;
        }
    }
}
