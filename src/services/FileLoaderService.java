package services;

import transformers.CustomTransformer;
import transformers.TextTransformer;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/* Responsable for loading and saving data
Loads transformers and chained transformers from package "transformers", from a file.
* Also saves custom transformers to a file.*/
public record FileLoaderService() {

    private static final Path PATH_TO_TRANSFORMERS = Paths
            .get(System.getenv("AppData"), "CleanText", "customTransformers");

    public static List<TextTransformer> getTextTransformers() {
        return Arrays.stream(TextTransformer.class.getPermittedSubclasses())
                .filter(clazz -> !clazz.equals(CustomTransformer.class))
                .map(FileLoaderService::createTransformerInstance)
                .sorted()
                .toList();
    }

    public static List<CustomTransformer> getCustomTransformers() {
        try {
            Files.createDirectories(PATH_TO_TRANSFORMERS);
            System.out.println(PATH_TO_TRANSFORMERS);
            try (Stream<Path> stream = Files.list(PATH_TO_TRANSFORMERS)) {
                return stream.map(Path::toFile)
                        .map(FileLoaderService::File2Transformer)
                        .toList();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static CustomTransformer File2Transformer(File file) {
        try {
            return ((CustomTransformer) new ObjectInputStream(new FileInputStream(file)).readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading custom transformer");
            throw new RuntimeException(e);
        }
    }

    public static void saveCustomTransformer(CustomTransformer customTransformer) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(PATH_TO_TRANSFORMERS.resolve(customTransformer.getPrettyName() + ".ser").toFile()))) {
            oos.writeObject(customTransformer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static TextTransformer createTransformerInstance(Class<?> aClass) {
        try {
            return (TextTransformer) aClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
