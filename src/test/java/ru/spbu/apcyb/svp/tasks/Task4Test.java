package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.file.Path;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

/**
 * Tests for task 4.
 */
class Task4Test {

  private static final Logger logger = Logger.getLogger(Task4Test.class.getName());

  @Test
  void singleThreadComputationTest() throws IOException {
    int numberOfLinesToRead = 5000;
    Path dataPath = Path.of("numbers.txt");
    Path resPath = Path.of("oneThreadTestResult.txt");

    try (FileWriter singleThreadComputationTest = new FileWriter("oneThreadTestResult.txt")) {
      Task4.singleThreadComputation(singleThreadComputationTest, "numbers.txt", numberOfLinesToRead);
    }

    try (BufferedReader dataReader = new BufferedReader(new FileReader(dataPath.toFile())); BufferedReader resReader = new BufferedReader(new FileReader(resPath.toFile()))) {
      String dataLine;
      String res;
      boolean result = true;
      for (int i = 0; i < numberOfLinesToRead; i++) {
        res = resReader.readLine();
        dataLine = dataReader.readLine();
        dataLine = String.valueOf(Math.tan(Double.parseDouble(dataLine)));
        if (!res.contains(dataLine)) {
          result = false;
          logger.info("In the final file, in line " + i + " the value " + res + " was found instead of " + dataLine);
        }
        assertTrue(result);
      }
    }
  }

  @Test
  void multiThreadComputationTest() throws IOException, InterruptedException, ExecutionException {
    String multiThreadFileWriterName = "multiThreadTestResult.txt";
    int numberOfLinesToRead = 5000;
    int numberOfThreads = 10;
    Path filePath1 = Path.of("oneThreadTestResult.txt");
    Path filePath2 = Path.of("multiThreadTestResult.txt");
    String resultLine;
    String[] answer = new String[numberOfLinesToRead];
    String answerLine;

    int numbersInAnswer = 0;
    try (BufferedReader singleThreadResTestReader = new BufferedReader(new FileReader(filePath1.toFile()))) {

      while ((answerLine = singleThreadResTestReader.readLine()) != null) {
        answer[numbersInAnswer] = answerLine;
        numbersInAnswer++;
      }
    }

    Task4.multiThreadComputation(multiThreadFileWriterName, "numbers.txt", numberOfThreads);
    int viewedNumbers = 0;
    try (BufferedReader multiThreadResTestReader = new BufferedReader(new FileReader(filePath2.toFile()))) {
      while ((resultLine = multiThreadResTestReader.readLine()) != null) {
        boolean containsAnswer = false;
        for (var line : answer) {
          if (line.contains(resultLine)) {
            containsAnswer = true;
            break;
          }
        }
        if (!containsAnswer) {
          logger.info("Error! No value found in response " + resultLine);
        }
        assertTrue(containsAnswer);
        viewedNumbers++;
      }
    }
    assertEquals(numbersInAnswer, viewedNumbers);
  }

  @Test
  void fileNotFoundTest() throws IOException {
    try (FileWriter fileWriter = new FileWriter("file", false)) {
      assertThrows(FileNotFoundException.class, () -> Task4.singleThreadComputation(fileWriter, "NotExistingFile", 1000));
    }
  }
}