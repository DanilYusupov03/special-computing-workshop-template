package ru.spbu.apcyb.svp.tasks;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Task3 Non-standard input and output.
 */
public class Task3 {

  private static final String WRONG_MAIN_ARGS_MSG = """
      Incorrect amount of arguments. Should be two strings: path to directory that should be
      scanned and path to file where a file tree will be saved.
      """;
  private static final String FILE_TREE_WRITE_ERROR_MSG =
      "Failed to write directory structure tree to file";

  private static final String DIR_ENTRIES_ERROR_MSG = "Failed to get entries of the directory";

  public static void main(String[] args) {
    if (args.length != 2) {
      throw new IllegalArgumentException(WRONG_MAIN_ARGS_MSG);
    }
    Path r = Path.of(args[0]);
    Path d = Path.of(args[1]);
    getDirectorySnapshot(r, d);
  }

  public static void getDirectorySnapshot(Path rootPath, Path destFilePath) {
    StringBuilder sb = new StringBuilder();
    buildTree(rootPath, 0, sb);
    String tree = sb.toString();
    System.out.println(tree);
    try (var file = new FileWriter(destFilePath.toFile())) {
      file.write(tree);
    } catch (IOException e) {
      throw new RuntimeException(FILE_TREE_WRITE_ERROR_MSG, e);
    }
  }

  public static void buildTree(Path file, int indent, StringBuilder sb) {
    sb.append(writeTabulation(indent))
        .append(file.getFileName())
        .append("/")
        .append("\n");
    try (var files = Files.list(file)) {
      files.forEach(f -> {
        if (Files.isDirectory(f)) {
          buildTree(f, indent + 1, sb);
        } else {
          sb.append(writeTabulation(indent + 1))
              .append(f.getFileName())
              .append("\n");
        }
      });
    } catch (IOException e) {
      throw new RuntimeException(DIR_ENTRIES_ERROR_MSG, e);
    }
  }

  private static String writeTabulation(int tabulation) {
    return "|  ".repeat(Math.max(0, tabulation));
  }
}
