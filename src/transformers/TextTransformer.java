package transformers;

import java.io.Serializable;

public sealed interface TextTransformer
        extends Comparable<TextTransformer>, Serializable
        permits CodeBlockTrail, CodeBlocksFix, CustomTransformer,
        OneLineBetweenParagraphs, RemoveHorizontalLines, RemoveLastParagraph,
        RemoveListSpacing, RemoveListing, RemoveListingFromTitles {

    String getPrettyName();
    String transform(String text);

    @Override
    default int compareTo(TextTransformer o) {
        return getPrettyName().compareTo(o.getPrettyName());
    }
}
