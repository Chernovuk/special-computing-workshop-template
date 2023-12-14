package ru.spbu.apcyb.svp.tasks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

/**
 * Task 4.
 */
public class Task4 {
  public static final Logger logger = Logger.getLogger(Task4.class.getName());

  /**
   * Generates file of random numbers.
   *
   * @param fileName name of a file where random numbers are written
   * @param count number of generated values
   * @throws IOException if there was an error during writing values to the file
   */
  public static void generateValues(String fileName, int count) throws IOException {
    try (FileWriter fileWriter = new FileWriter(fileName)) {
      var random = new SecureRandom();
      for (int i = 0; i < count; ++i) {
        fileWriter.write((random.nextDouble(1000) - 500) + System.lineSeparator());
      }
      fileWriter.flush();
    }
  }

  /**
   * Writes values from file into List.
   *
   * @param inputFile file from where it reads values
   * @return list of read values
   */
  public static List<Double> valuesFromFileToList(File inputFile) throws IOException {
    try (Scanner input = new Scanner(inputFile)) {
      List<Double> values = new ArrayList<>();
      while (input.hasNextLine()) {
        values.add(Double.valueOf(input.nextLine()));
      }
      return values;

    } catch (IOException e) {
      throw new IOException(e);
    }
  }

  /**
   * Writing results to a specified file.
   *
   * @param outputFile file where values are written to
   * @param values list of values that are going to be written into file
   * @throws IOException if there was an error during writing to the file
   *     or creating BufferedWriter
   */
  public static void writeResultToFile(File outputFile, List<Double> values) throws IOException {
    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
      bufferedWriter.write("Computed %d numbers:%n".formatted(values.size()));
      for (double v : values) {
        try {
          bufferedWriter.write(String.valueOf(v));
          bufferedWriter.newLine();
        } catch (IOException exception) {
          throw new IOException(exception);
        }
      }
    }
  }

  /**
   * Computes tangents of numbers with only one thread.
   *
   * @param values list of values which tangents are computed
   * @return list of computed tangents values
   */
  public static List<Double> computeTangentWithOneThread(List<Double> values) {
    long startTime = System.nanoTime();
    List<Double> result = new ArrayList<>();
    for (double v : values) {
      result.add(Math.tan(v));
    }
    long finishTime = System.nanoTime();
    logger.info("Time of computing tangents of " + values.size() + " numbers in single thread is: "
            + (finishTime - startTime) + " nano seconds");
    return result;
  }

  /**
   * Computes tangents of numbers with multiple threads.
   *
   * @param values list of values which tangents are computed.
   * @param numberOfThreads number of threads which are going to be used
   * @return list of computed tangent values
   * @throws ExecutionException if there was an error during starting the threads
   * @throws InterruptedException if there was an interruption of a thread
   */
  public static List<Double> computeTangentWithMultiThreads(
          List<Double> values, int numberOfThreads)
          throws ExecutionException, InterruptedException {
    long startTime = System.nanoTime();
    ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
    try {
      Future<List<Double>> futures = executorService.submit(
              () -> values.parallelStream().map(Math::tan).toList());
      long finishTime = System.nanoTime();
      logger.info("Time of computing tangents of " + values.size() + " numbers in multi thread is: "
              + (finishTime - startTime) + " nano seconds");
      return futures.get();
    } finally {
      executorService.shutdownNow();
    }
  }

  /**
   * Method running the programme, where number of values and number of threads are specified.
   *
   * @param args console arguments
   * @throws IOException if there was an error during reading or writing a file
   * @throws ExecutionException if there was an error during start of the threads
   * @throws InterruptedException if there was an interruption of a thread
   */
  public static void main(String[] args)
          throws IOException, ExecutionException, InterruptedException {
    int numberOfValues = 10000000;
    int numberOfThreads = 6;
    String inputFile = "./src/main/resources/Numbers.txt";
    String outputFile = "./src/main/resources/Results.txt";
    generateValues(inputFile, numberOfValues);
    List<Double> values = valuesFromFileToList(new File(inputFile));
    computeTangentWithOneThread(values);
    List<Double> result = computeTangentWithMultiThreads(values, numberOfThreads);
    writeResultToFile(new File(outputFile), result);
  }
}