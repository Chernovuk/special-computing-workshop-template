package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.Test;




class Task1Test {

  @Test
  void inputSumForExchange() {

    System.setIn(new ByteArrayInputStream("300000000".getBytes()));
    assertEquals(300000000, Task1.inputSumForExchange());
    System.setIn(System.in);

    System.setIn(new ByteArrayInputStream("fff".getBytes()));
    assertThrows(IllegalArgumentException.class, Task1::inputSumForExchange);
    System.setIn(System.in);

    System.setIn(new ByteArrayInputStream("-3".getBytes()));
    assertThrows(IllegalArgumentException.class, Task1::inputSumForExchange);
    System.setIn(System.in);
  }


  @Test
  void inputNominals() {

    System.setIn(new ByteArrayInputStream("2 1 5".getBytes()));
    long[] expected = {2, 1, 5};
    long[] actual = Task1.inputNominals();
    System.setIn(System.in);
    assertArrayEquals(expected, actual);

    System.setIn(new ByteArrayInputStream("2 avc".getBytes()));
    boolean thrown = false;
    try {
      Task1.inputNominals();
      System.setIn(System.in);
    } catch (Exception e) {
      thrown = true;
    }
    assertTrue(thrown);

    System.setIn(new ByteArrayInputStream("-3 5".getBytes()));
    thrown = false;
    try {
      Task1.inputNominals();
      System.setIn(System.in);
    } catch (Exception e) {
      thrown = true;
    }
    assertTrue(thrown);
  }


  @Test
  void shrinkNominals() {

    int n = 5;
    long[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    long[] expected = {1, 2, 3, 4, 5};
    long[] actual = Task1.shrinkNominals(array, n);

    assertArrayEquals(expected, actual);
  }


  @Test
  void modifyNominals() {

    long sumForExchange = 7;
    long[] nominals = {6, 2, 5, 10, 7, 2, 20};
    long[] expected = {2, 5, 6, 7};
    long[] actual = Task1.modifyNominals(sumForExchange, nominals);
    assertArrayEquals(expected, actual);

    boolean thrown = false;
    long[] nominals1 = {-2, 1, 6};
    try {
      Task1.modifyNominals(sumForExchange, nominals1);
    } catch (Exception e) {
      thrown = true;
    }
    assertTrue(thrown);
  }

  @Test
  void modifyNominals4() {

    long numb = 15;
    long[] val = {2, 3, 5};

    long[] exp = {2, 3, 5};
    long[] act = Task1.modifyNominals(numb, val);

    assertArrayEquals(exp, act);
  }


  @Test
  void exchange() {

    long initialSumForExchange = 1000;
    long sumForExchange = 1000;
    long[] nominals = {500, 1};
    long[] numberOfNominalsOccurrences = new long[nominals.length];
    int helper = 0;
    long n = 0;

    long expected = 3;
    long actual = Task1.exchange(initialSumForExchange, sumForExchange, nominals,
            numberOfNominalsOccurrences, helper, n);

    assertEquals(expected, actual);
  }
}
