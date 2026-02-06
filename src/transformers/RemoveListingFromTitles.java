package transformers;

public class RemoveListingFromTitles implements TextTransformer {

    @Override
    public String getPrettyName() {
        return "Remove listing from titles";
    }

    @Override
    public String transform(String text) {
        return text
            .replaceAll("(?m)(^#+)(\\s+\\d+\\.\\s+)", "$1 ")
            .replaceAll("(?m)(^#+)(\\s+-\\s+)", "$1 ");
    }
}
