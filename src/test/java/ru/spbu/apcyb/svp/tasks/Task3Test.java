package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemException;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

class Task3Test {

  @Test
  void checkConsoleArgumentsLength() {
    String[] args = {"Dir", "file.txt", "..//"};
    assertThrows(IllegalArgumentException.class, () -> Task3.checkConsoleArguments(args));
  }

  @Test
  void checkConsoleArgumentsFileIsDirectory() {
    String[] args = {"file.txt", "..//"};
    assertThrows(FileSystemException.class, () -> Task3.checkConsoleArguments(args));
  }

  @Test
  void checkConsoleArgumentsFileAlreadyExists() {
    String[] args = {"..//", "src/test/java/ru/spbu/apcyb/svp/tasks/ExampleTestClass.java"};
    assertThrows(FileAlreadyExistsException.class, () -> Task3.checkConsoleArguments(args));
  }

  @Test
  void checkConsoleArgumentsNonExistentDirectory() {
    String[] args = {"smth/", "file.txt"};
    assertThrows(FileNotFoundException.class, () -> Task3.checkConsoleArguments(args));
  }

  @Test
  void searchFiles() throws IOException {
    String[] args = {".\\src\\test\\java\\ru\\spbu\\apcyb\\svp\\tasks\\", ".\\src\\test\\java\\ru"
            + "\\spbu\\apcyb\\svp\\tasks\\SubDir\\result.txt"};
    Task3.main(args);
    File actual = new File(".\\src\\test\\java\\ru\\spbu\\apcyb\\svp\\tasks\\result.txt");
    File expected = new File(".\\src\\test\\java\\ru\\spbu\\apcyb\\svp\\tasks\\expected.txt");
    assertTrue(FileUtils.contentEquals(actual, expected));
  }
}