package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;


/**
 * MyArrayList tests.
 */
class MyArrayListTest {

  MyArrayList list = new MyArrayList();

  @Test
  void toStringTest() {
    MyArrayList myList = new MyArrayList();
    assertEquals("[]", myList.toString());

    myList.add(5);
    myList.add(8);
    assertEquals("[5, 8]", myList.toString());
  }

  @Test
  void add() {
    MyArrayList myList = new MyArrayList();
    List<String> expected = new ArrayList<>();

    myList.add("apple");
    myList.add("banana");

    expected.add("apple");
    expected.add("banana");

    assertEquals(expected.toString(), myList.toString());

    Object o = null;
    assertThrows(NullPointerException.class, () -> myList.add(o));
  }

  @Test
  void addAtIndex() {
    MyArrayList myList = new MyArrayList();
    myList.add("apple");
    myList.add("orange");
    myList.add("banana");
    myList.add(1, "peach");

    List<String> expected = new ArrayList<>();
    expected.add("apple");
    expected.add("peach");
    expected.add("orange");
    expected.add("banana");

    assertEquals(expected.toString(), myList.toString());

    Object o = null;
    assertThrows(NullPointerException.class, () -> myList.add(o));

    assertThrows(IndexOutOfBoundsException.class, () -> myList.add(7, "something"));
  }

  @Test
  void remove() {
    MyArrayList myList = new MyArrayList();
    List<String> expected = new ArrayList<>();

    myList.add("apple");
    myList.add("banana");

    expected.add("apple");
    expected.add("banana");

    myList.remove(1);
    expected.remove(1);
    assertEquals(expected.toString(), myList.toString());
  }

  @Test
  void removeFromEmpty() {
    MyArrayList myList = new MyArrayList();

    assertThrows(IndexOutOfBoundsException.class, () -> myList.remove(0));
  }

  @Test
  void containsApple() {
    MyArrayList myList = new MyArrayList();
    myList.add("apple");
    myList.add("orange");
    assertTrue(myList.contains("apple"));

    myList.remove(0);
    assertFalse(myList.contains("apple"));
  }

  @Test
  void isEmpty() {
    MyArrayList stack = new MyArrayList();
    stack.add("apple");
    assertFalse(stack.isEmpty());

    stack.remove(0);
    assertTrue(stack.isEmpty());
  }

  @Test
  void get() {
    MyArrayList myList = new MyArrayList();
    myList.add("apple");
    myList.add("orange");
    myList.add("banana");

    assertEquals("banana", myList.get(2));

    assertThrows(IndexOutOfBoundsException.class, () -> list.get(5));
  }

  @Test
  void size() {
    MyArrayList myList = new MyArrayList();
    myList.add("apple");
    myList.add("orange");
    myList.add("banana");
    assertEquals(3, myList.size());

    myList.remove(0);
    assertEquals(2, myList.size());
  }

  @Test
  void addAll() {
    Collection<Object> c = null;
    assertThrows(UnsupportedOperationException.class, () -> list.addAll(c));
  }

  @Test
  void clear() {
    assertThrows(UnsupportedOperationException.class, () -> list.clear());
  }

  @Test
  void containsAll() {
    Collection<Object> c = null;
    assertThrows(UnsupportedOperationException.class, () -> list.containsAll(c));
  }

  @Test
  void indexOf() {
    assertThrows(UnsupportedOperationException.class, () -> list.indexOf("str"));
  }

  @Test
  void iterator() {
    assertThrows(UnsupportedOperationException.class, () -> list.iterator());
  }

  @Test
  void lastIndexOf() {
    assertThrows(UnsupportedOperationException.class, () -> list.lastIndexOf("str"));
  }

  @Test
  void listIterator() {
    assertThrows(UnsupportedOperationException.class, () -> list.listIterator());
  }

  @Test
  void removeAll() {
    Collection<Object> c = null;
    assertThrows(UnsupportedOperationException.class, () -> list.removeAll(c));
  }

  @Test
  void retainAll() {
    Collection<Object> c = null;
    assertThrows(UnsupportedOperationException.class, () -> list.retainAll(c));
  }

  @Test
  void set() {
    assertThrows(UnsupportedOperationException.class, () -> list.set(0, "str"));
  }

  @Test
  void subList() {
    assertThrows(UnsupportedOperationException.class, () -> list.subList(0, 2));
  }

  @Test
  void toArray() {
    assertThrows(UnsupportedOperationException.class, () -> list.toArray());
  }
}