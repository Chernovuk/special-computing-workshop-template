package ru.spbu.apcyb.svp.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Task3.
 */
public class Task3 {

  /**
   * Checks whether console arguments were inputted correctly.
   *
   * @param args console arguments
   * @throws IOException if console arguments are wrong in some way
   */
  public static void checkConsoleArguments(String[] args) throws IOException {
    if (args.length != 2) {
      throw new IllegalArgumentException("There should be exactly two parameters in the console!");
    }
    File file = new File(args[1]);
    if (file.isDirectory()) {
      throw new FileSystemException("Second argument should be file, not directory!");
    }
    if (Files.exists(Path.of(file.getPath()))) {
      throw new FileAlreadyExistsException("There is already such a file!");
    }
    File directory = new File(args[0]);
    if (directory.listFiles() == null) {
      throw new FileNotFoundException("There is no such a directory!");
    }
  }

  /**
   * creates FileWriter.
   *
   * @param file file in which we are going to write results of programme
   * @return created FileWriter
   * @throws IOException if there was an error during creation of a FileWriter
   */
  public static FileWriter createFileWriter(String file) throws IOException {
    FileWriter fileWriter;
    try {
      fileWriter = new FileWriter(file, false);
    } catch (IOException e) {
      throw new IOException("Error during creation of a fileWriter!");
    }
    return fileWriter;
  }

  /**
   * Looks at designated directory and writes paths to its files and directories to
   * earlier created FileWriter.
   *
   * @param directory directory which we are looking at
   * @param fileWriter FileWriter created in advance
   * @param h helping index for right tabulation
   * @throws IOException if there are any problems with FileWriter operations
   */
  static void searchFiles(String directory, FileWriter fileWriter, int h) throws IOException {

    File[] listOfFiles = new File(directory).listFiles();
    assert listOfFiles != null;
    for (File f : listOfFiles) {
      if (f.isDirectory()) {
        for (int i = 0; i < h; ++i) {
          fileWriter.append(' ');
        }

        fileWriter.write("Directory: " + f.getPath());
        fileWriter.append('\n');

        searchFiles(f.getAbsolutePath(), fileWriter, h + 1);
      } else {
        for (int i = 0; i < h; ++i) {
          fileWriter.append(' ');
        }
        fileWriter.write("File: " + f.getAbsoluteFile());
        fileWriter.append('\n');
      }
    }
  }

  /**
   * Method that runs the programme.
   *
   * @param args console arguments (Should be directory of a files and file name)
   * @throws IOException if some of the used methods throw an exception
   */
  public static void main(String[] args) throws IOException {
    checkConsoleArguments(args);
    FileWriter fileWriter = createFileWriter(args[1]);
    searchFiles(args[0], fileWriter, 0);
    fileWriter.close();
  }
}