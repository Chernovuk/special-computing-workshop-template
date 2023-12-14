package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;





/**
 * Tests for Task4.
 */
class Task4Test {
  @Test
  void generateValues() throws IOException {
    int numberOfValues = 1000;
    String inputFile = "./src/main/resources/Numbers.txt";
    Task4.generateValues(inputFile, numberOfValues);
    List<Double> values = Files.readAllLines(Path.of(inputFile)).stream()
            .map(Double::valueOf).toList();
    for (double v : values) {
      assertTrue(-500 <= v && v < 500);
    }
  }

  @Test
  void valuesFromFileToList() throws IOException {
    int numberOfValues = 1000;
    String inputFile = "./src/main/resources/Numbers.txt";
    Task4.generateValues(inputFile, numberOfValues);
    List<Double> expected = Files.readAllLines(Path.of(inputFile)).stream()
            .map(Double::valueOf).toList();
    List<Double> actual = Task4.valuesFromFileToList(new File(inputFile));
    assertArrayEquals(expected.toArray(), actual.toArray());
  }

  @Test
  void computeTangentWithOneThread() throws IOException {
    int numberOfValues = 1000;
    String inputFile = "./src/main/resources/Numbers.txt";
    Task4.generateValues(inputFile, numberOfValues);
    List<Double> values = Task4.valuesFromFileToList(new File(inputFile));
    List<Double> actual = Task4.computeTangentWithOneThread(values);
    for (int i = 0; i < actual.size() && i < values.size(); ++i) {
      assertEquals(Math.tan(values.get(i)), actual.get(i));
    }
  }

  @Test
  void computeTangentWithMultiThread()
          throws ExecutionException, InterruptedException, IOException {
    int numberOfValues = 1000;
    String inputFile = "./src/main/resources/Numbers.txt";
    Task4.generateValues(inputFile, numberOfValues);
    List<Double> values = Task4.valuesFromFileToList(new File(inputFile));
    assertEquals(Task4.computeTangentWithOneThread(values),
            Task4.computeTangentWithMultiThreads(values, 6));
  }
}