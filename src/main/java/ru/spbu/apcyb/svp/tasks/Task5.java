package ru.spbu.apcyb.svp.tasks;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

/**
 * Task5 Stream API.
 */
public class Task5 {

  public static void main(String[] args) throws IOException {
    Path textFile = Path.of("src/main/resources/task5/text.txt");
    Path countFile = Path.of("src/main/resources/task5/count.txt");
    Path wordsFolder = Path.of("src/main/resources/task5/words/");

    writeWords(countWords(textFile), countFile, wordsFolder);
  }

  public static Map<String, Long> countWords(Path textFile) {
    try (var fileStream = Files.lines(textFile)) {
      return fileStream
          .flatMap(line -> Arrays.stream(line.split("[^a-zA-ZЁёА-я0-9]")))
          .filter(not(String::isEmpty))
          .map(String::toLowerCase)
          .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    } catch (IOException e) {
      throw new RuntimeException("IO exception", e);
    }
  }

  public static void writeWords(Map<String, Long> wordCount, Path countFile, Path wordsFolder) throws IOException {
    try (BufferedWriter writer = Files.newBufferedWriter(countFile)) {
      wordCount.forEach((word, count) -> {
        String line = word + " " + count + "\n";
        try {
          writer.write(line);
        } catch (IOException e) {
          throw new RuntimeException("Error while writing word count for line " + line, e);
        }

        CompletableFuture.runAsync(() -> {
          try (var wordWriter = Files.newBufferedWriter(wordsFolder.resolve(word + ".txt"))) {
            wordWriter.write((word + " ").repeat(Math.toIntExact(count - 1)) + word + "\n");
          } catch (IOException e) {
            throw new RuntimeException("Error while writing word " + word, e);
          }
        });
      });
    }
  }
}