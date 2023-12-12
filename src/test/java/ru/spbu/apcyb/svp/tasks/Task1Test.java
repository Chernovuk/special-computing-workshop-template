package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.Test;




class Task1Test {

  @Test
  void inputSumForExchange_300000000() {
    System.setIn(new ByteArrayInputStream("300000000".getBytes()));
    assertEquals(300000000, Task1.inputSumForExchange());
    System.setIn(System.in);
  }

  @Test
  void inputSumForExchange_String() {
    System.setIn(new ByteArrayInputStream("fff".getBytes()));
    assertThrows(IllegalArgumentException.class, Task1::inputSumForExchange);
    System.setIn(System.in);
  }

  @Test
  void inputSumForExchange_Negative() {
    System.setIn(new ByteArrayInputStream("-3".getBytes()));
    assertThrows(IllegalArgumentException.class, Task1::inputSumForExchange);
    System.setIn(System.in);
  }


  @Test
  void inputNominals_2_1_5() {

    System.setIn(new ByteArrayInputStream("2 1 5".getBytes()));
    long[] expected = {2, 1, 5};
    long[] actual = Task1.inputNominals();
    System.setIn(System.in);
    assertArrayEquals(expected, actual);
  }

  @Test
  void inputNominals_2_String() {
    System.setIn(new ByteArrayInputStream("2 avc".getBytes()));
    boolean thrown = false;
    try {
      Task1.inputNominals();
      System.setIn(System.in);
    } catch (Exception e) {
      thrown = true;
    }
    assertTrue(thrown);
  }

  @Test
  void modifyNominals_sum_7_noms_6_2_5_6_7_2() {
    long sumForExchange = 7;
    long[] nominals = {6, 2, 5, 6, 7, 2};
    long[] expected = {2, 5, 6, 7};
    long[] actual = Task1.modifyNominals(sumForExchange, nominals);
    assertArrayEquals(expected, actual);
  }

  @Test
  void modifyNominals_Negative() {
    long sumForExchange = 7;
    long[] nominals = {-2, 1, 6};
    assertThrows(IllegalArgumentException.class,
            () -> Task1.modifyNominals(sumForExchange, nominals));
  }

  @Test
  void modifyNominals_ValueGreaterThanSum_7() {
    long sumForExchange = 7;
    long[] nominals  = {5, 2, 10, 8, 1};
    assertThrows(IllegalArgumentException.class,
            () -> Task1.modifyNominals(sumForExchange, nominals));
  }

  @Test
  void modifyNominalsToStayTheSame() {
    long sumForExchange = 15;
    long[] nominals = {2, 3, 5};

    long[] expected = {2, 3, 5};
    long[] actual = Task1.modifyNominals(sumForExchange, nominals);

    assertArrayEquals(expected, actual);
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
