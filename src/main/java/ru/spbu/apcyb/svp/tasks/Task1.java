package ru.spbu.apcyb.svp.tasks;

import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Task1.
 */
public class Task1 {

  private static final Logger logger = Logger.getLogger(Task1.class.getName());

  /**
   * Method for inputting initial sum for exchange.
   *
   * @return the entered sum
   */
  public static long inputSumForExchange() {

    Scanner scanner = new Scanner(System.in);
    String str = scanner.nextLine();
    long sumForExchange;

    try {
      sumForExchange = Long.parseLong(str);
    } catch (Exception e) {
      throw new IllegalArgumentException("Input error. Exchange sum was typed incorrectly!");
    }

    if (sumForExchange <= 0) {
      throw new IllegalArgumentException("Exchange sum must be greater than 0!");
    }

    return sumForExchange;
  }

  /**
   * Method for inputting all available nominals for exchange.
   *
   * @return array of entered nominals
   */
  public static long[] inputNominals() {

    Scanner scanner = new Scanner(System.in);
    String str = scanner.nextLine();

    if (str.matches("(\\d+\\s*)+")) {
      String[] denominations = str.split(" ");

      long[] nominals = new long[denominations.length];

      for (int i = 0; i < nominals.length; ++i) {
        nominals[i] = Long.parseLong(denominations[i]);
      }

      return nominals;
    }

    throw new IllegalArgumentException("Input error. Nominals should be positive numbers, "
            + "separated by a space symbol!");
  }

  /**
   * Method for deleting excessive last elements.
   *
   * @param nominals array of nominals
   * @param n new size of our array
   * @return array of nominals with new length n
   */
  public static long[] shrinkNominals(long[] nominals, int n) {

    long[] shrunkNominals = new long[n];

    System.arraycopy(nominals, 0, shrunkNominals, 0, n);

    return shrunkNominals;
  }

  /**
   * Method for modifying array of nominals - deleting all needless values.
   *
   * @param sumForExchange initial sum for exchange
   * @param nominals array of nominals
   * @return edited array of nominals
   */
  public static long[] modifyNominals(long sumForExchange, long[] nominals) {

    Arrays.sort(nominals);

    if (nominals[0] <= 0) {
      throw new IllegalArgumentException("Input error. Nominals can't be negative!");
    }

    //Deleting nominals greater than sumForExchange
    int i = 0;
    while (i < nominals.length && sumForExchange >= nominals[i]) {
      ++i;
    }
    nominals = shrinkNominals(nominals, i);

    //Deleting duplicates of available nominals
    nominals = Arrays.stream(nominals).distinct().toArray();

    return nominals;
  }

  private static void printCombination(long[] nominals, long[] numberOfNominalOccurrences) {

    logger.log(Level.INFO, "Combination:");
    for (int i = 0; i < numberOfNominalOccurrences.length; ++i) {
      for (int j = 0; j < numberOfNominalOccurrences[i]; ++j) {
        logger.log(Level.INFO, "{0} ", nominals[i]);
      }
    }
    logger.log(Level.INFO, "\n");
  }

  /**
   * Method for finding all possible exchanges.
   *
   * @param initialSumForExchange initial sum for exchange
   * @param sumForExchange current remaining part of initial sumForExchange
   * @param nominals array of available nominals
   * @param numberOfNominalOccurrences number of occurrences of certain nominal
   * @param h index needed to prevent computing the same combination up to the exact permutation
   * @param n number of counted combinations
   * @return number of computed combinations
   */
  public static long exchange(long initialSumForExchange, long sumForExchange, long[] nominals,
                              long[] numberOfNominalOccurrences, int h, long n) {

    if (nominals.length == 0) {
      return 0;
    }

    //Number of complete occurrences in initial sum
    long div = initialSumForExchange / nominals[h];

    for (int i = 0; i <= div; ++i) {
      if (sumForExchange >= 0) {
        numberOfNominalOccurrences[h] = i;

        if (sumForExchange == 0) {
          ++n;
          printCombination(nominals, numberOfNominalOccurrences);
        } else if (h + 1 < nominals.length) {
          n = exchange(initialSumForExchange, sumForExchange, nominals,
                  numberOfNominalOccurrences, h + 1, n);
        }
      } else {
        return n;
      }

      sumForExchange -= nominals[h];
    }
    return n;
  }

  /**
   * Method running programme.
   *
   * @param args command line values
   */
  public static void main(String[] args) {

    long sumForExchange;   //Sum for exchange

    long[] nominals;    //Available nominals

    logger.log(Level.INFO, "Input sum for exchange:");
    try {
      sumForExchange = inputSumForExchange();

      logger.log(Level.INFO, "Input nominals for exchange:");
      nominals = inputNominals();
      nominals = modifyNominals(sumForExchange, nominals);

      //Number of possible exchanges
      long numberOfCombos = exchange(sumForExchange, sumForExchange, nominals,
              new long[nominals.length], 0, 0);
      if (numberOfCombos == 0) {
        logger.log(Level.INFO, "There is no way to exchange sum with inputted nominals.");
      } else {
        logger.log(Level.INFO, "Number of possible combinations = {0}", numberOfCombos);
      }
    } catch (Exception e) {
      logger.log(Level.INFO, e.getMessage());
    }
  }
}
