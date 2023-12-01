package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class Task3Test {

  @Test
  void directoryScanTest() throws IOException {
    Path directoryPath = Path.of("src/test/resources/directory");
    Path treeFilePath = Path.of("src/test/resources/tree.txt");
    Path pathToFile = Path.of("src/test/resources/tree_expected.txt");

    String g = """
        directory/
        |  test1.txt
        |  test2.txt
        |  test_dir/
        |  |  test_dir2.txt
        |  |  test_dir2/
        |  |  |  test_dir3/
        |  |  |  |  test_dir3.txt
        |  |  test_dir1.txt
                        """;

    try (FileWriter output = new FileWriter(String.valueOf(pathToFile))) {
      output.write(g);
    }

    Task3.getDirectorySnapshot(directoryPath, treeFilePath);

    if (Files.notExists(treeFilePath)) {
      fail("Cannot find result file at expected path.");
    }

    assertEquals(-1, Files.mismatch(pathToFile, treeFilePath));
  }
}