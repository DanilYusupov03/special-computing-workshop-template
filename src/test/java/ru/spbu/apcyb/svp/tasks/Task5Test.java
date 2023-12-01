package ru.spbu.apcyb.svp.tasks;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.spbu.apcyb.svp.tasks.Task5.countWords;
import static ru.spbu.apcyb.svp.tasks.Task5.writeWords;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;


class Task5Test {

  @Test
  void runTextProcessing() throws IOException {
    int count = 0;
    Path textPath = Path.of("src/test/resources/task5/TestTask5.txt");
    Path countPath = Path.of("src/test/resources/task5/countsTestFile.txt");
    Path wordsFolder = Path.of("src/test/resources/task5/");
    writeWords(countWords(textPath), countPath, wordsFolder);

    String[] words = {"foo", "bar", "baz"};
    for (String word : words) {
      Path path = wordsFolder.resolve(word + ".txt");
      if (Files.exists(path)) {
        count++;
        Files.delete(path);
      }
    }

    Path expectedCount = Path.of("src/test/resources/task5/TestTask5_expected.txt");
    assertEquals(words.length, count);
    assertEquals(-1, Files.mismatch(countPath, expectedCount));
  }

}