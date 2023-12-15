package ru.spbu.apcyb.svp.tasks;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Task 5.
 */
public class Task5 {

  /**
   * Reads file into map and counts number of occurrences of words.
   *
   * @param fileName file that is read
   * @return map of words and their number of occurrences
   * @throws FileNotFoundException if the file could not be found
   */
  public static Map<String, Long> readFileAndCountWords(String fileName)
          throws FileNotFoundException {
    try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
      return stream
              .flatMap(line -> Stream.of(line.split(" ")))
              .map(word -> word.replaceAll("[^\\p{L}\\p{N}]+", ""))
              .filter(word -> !word.isEmpty())
              .map(String::toLowerCase)
              .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    } catch (IOException e) {
      throw new FileNotFoundException("There is no such a file!");
    }
  }

  /**
   * Writes words and number of their occurrences in the specified file.
   *
   * @param wordCount map of words and number of their occurrences
   * @param countsFile file in which where we are writing words and their counts
   * @throws IOException if there was an error during writing in file
   */
  public static void writeWordsWithCountsToFile(Map<String, Long> wordCount, String countsFile)
          throws IOException {
    try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(countsFile))) {
      for (Map.Entry<String, Long> entry : wordCount.entrySet()) {
        String word = entry.getKey();
        long wordNumber = entry.getValue();
        String line = word + " = " + wordNumber;
        writer.write(line);
        writer.newLine();
      }
    }
  }

  /**
   * Writes word to already existing file wordCount times.
   *
   * @param word word, which we are writing
   * @param wordCount number of word's occurrences
   */
  public static void wordsToExistingFiles(String word, Long wordCount)
          throws FileNotFoundException {
    try (BufferedWriter wordFile = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream((word + ".txt"))))) {
      for (int i = 0; i < wordCount; i++) {
        wordFile.write(word);
        wordFile.write("\n");
      }
    } catch (IOException e) {
      throw new FileNotFoundException("There is no such a file: " + word + ".txt");
    }
  }

  /**
   * Creates a file with the word name and the writes in it using wordsToExistingFiles.
   *
   * @param word the word
   * @param wordCount number of word's occurrences
   */
  public static void wordsToNonExistingFile(String word, Long wordCount)
          throws FileNotFoundException {
    try (FileWriter wordFile = new FileWriter(word + ".txt", true)) {
      wordsToExistingFiles(word, wordCount);
    } catch (IOException ex) {
      throw new FileNotFoundException("new file for new word " + word + "did not open");
    }
  }

  /**
   * Combines wordsToExistingFiles and wordsToNonExistingFile.
   *
   * @param wordCount map of words and number of their occurrences
   * @throws RuntimeException if there was an error during work with files
   */
  public static void writeWordsToSeparateFiles(Map<String, Long> wordCount)
          throws RuntimeException {
    for (Map.Entry<String, Long> entry : wordCount.entrySet()) {
      String word = entry.getKey();
      long wordNumber = entry.getValue();
      CompletableFuture.runAsync(() -> {
        try {
          if (Files.exists(Path.of(word + ".txt"))) {
            wordsToExistingFiles(word, wordNumber);
          } else {
            wordsToNonExistingFile(word, wordNumber);
          }
        } catch (IOException e) {
          throw new RuntimeException("IOException occurred");
        }
      });
    }
  }

  /**
   * Method running the programme.
   *
   * @param args console arguments
   * @throws IOException if there was an error during reading or writing files
   */
  public static void main(String[] args) throws IOException {
    String inputFileName = ".\\src\\main\\resources\\WarAndPeaceEn.txt";
    String outputFileName = "Counts.txt";

    Map<String, Long> wordCount = readFileAndCountWords(inputFileName);
    writeWordsWithCountsToFile(wordCount, outputFileName);
    writeWordsToSeparateFiles(wordCount);

  }
}
