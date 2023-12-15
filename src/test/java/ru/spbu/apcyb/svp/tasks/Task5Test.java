package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;



/**
 * Tests for task5.
 */
public class Task5Test {

  private final String inputTest = ".\\src\\main\\resources\\inputTest.txt";

  @Test
  void readFileAndCountWordsOninputTest() throws IOException {
    Map<String, Long> expected = new HashMap<>();
    expected.put("foo", 2L);
    expected.put("bar", 1L);
    expected.put("baz", 1L);
    Map<String, Long> actual = Task5.readFileAndCountWords(inputTest);
    assertEquals(expected, actual);
  }

  @Test
  void readFileAndCountWordsNonExistingInput() {
    String inputTest = ".\\src\\main\\resources\\Doctor_Zhivago.txt";
    assertThrows(FileNotFoundException.class, () -> Task5.readFileAndCountWords(inputTest));
  }

  @Test
  void writeWordsWithCountsToFile() throws IOException {
    Map<String, Long> actualMap = Task5.readFileAndCountWords(inputTest);
    String outputTest = ".\\src\\main\\resources\\outputTest.txt";
    Task5.writeWordsWithCountsToFile(actualMap, outputTest);
    File actual = new File(outputTest);
    File expected = new File(".\\src\\main\\resources\\countsTestExpected.txt");
    assertTrue(FileUtils.contentEquals(expected, actual));
  }

  @Test
  void wordsToExistingFile() throws FileNotFoundException {
    Task5.wordsToExistingFiles("foo", 2L);
    Path fooPath = Path.of("foo.txt");
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fooPath.toFile()))) {
      String currentLine = bufferedReader.readLine();
      assertEquals("foo", currentLine);
    } catch (IOException e) {
      throw new RuntimeException("Проблема при записи в существующий файл");
    }
  }

  @Test
  void wordsToNonExistingFile() throws IOException {
    boolean fileDidNotExist = false;
    Path barPath = Path.of("bar" + ".txt");
    Files.delete(barPath);
    if (!Files.exists(barPath)) {
      Task5.wordsToNonExistingFile("bar", 1L);
      fileDidNotExist = true;
    }
    boolean fileExists = Files.exists(barPath);
    boolean check = fileExists && fileDidNotExist;

    assertTrue(check);
  }

  @Test
  void writeWordsToSeparateFiles() throws IOException {
    Map<String, Long> map = Task5.readFileAndCountWords(inputTest);
    Task5.writeWordsToSeparateFiles(map);
    Path directory = Path.of(".//");
    Set<String> expectedPaths = map.keySet();
    Set<String> pathsToRemove = new HashSet<>(Arrays.asList(
            "special-computing-workshop.iml", "images", ".gitignore",
            "src", "org.apache.commons.io-2.4.jar", "pom.xml",
            ".github", "target", ".git", ".idea", "google_checks.xml", "LICENCE", "LICENSE"));
    Set<String> actualPaths = new HashSet<>();
    try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(directory)) {
      for (Path path : dirStream) {
        actualPaths.add(String.valueOf(path.getFileName()).replace(".txt", ""));
      }
    }

    actualPaths.removeAll(pathsToRemove);
    assertEquals(expectedPaths, actualPaths);
  }

}
