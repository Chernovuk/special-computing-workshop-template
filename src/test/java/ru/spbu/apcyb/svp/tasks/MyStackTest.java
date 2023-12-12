package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.EmptyStackException;
import java.util.Stack;
import org.junit.jupiter.api.Test;



/**
 * MyStack tests.
 */
class MyStackTest {

  MyStack stack = new MyStack();

  @Test
  void toStringTest() {
    MyStack myStack = new MyStack();
    assertEquals("[]", myStack.toString());

    myStack.push(5);
    myStack.push(8);
    assertEquals("[5, 8]", myStack.toString());
  }

  @Test
  void push() {
    MyStack myStack = new MyStack();
    myStack.push("apple");
    myStack.push("banana");

    Stack<Object> expected = new Stack<>();
    expected.push("apple");
    expected.push("banana");

    assertEquals(expected.toString(), myStack.toString());
  }

  @Test
  void pop() {
    MyStack myStack = new MyStack();
    myStack.push("apple");
    myStack.push("banana");

    Stack<Object> expected = new Stack<>();
    expected.push("apple");
    expected.push("banana");

    myStack.pop();
    expected.pop();
    assertEquals(expected.toString(), myStack.toString());

    myStack.pop();
    assertThrows(EmptyStackException.class, myStack::pop);
  }

  @Test
  void peek() {
    MyStack myStack = new MyStack();
    myStack.push("apple");
    myStack.push("banana");
    assertEquals("banana", myStack.peek());
  }

  @Test
  void isEmpty() {
    MyStack myStack = new MyStack();
    assertTrue(myStack.isEmpty());
  }

  @Test
  void addAll() {
    Collection<Object> c = null;
    assertThrows(UnsupportedOperationException.class, () -> stack.addAll(c));
  }

  @Test
  void clear() {
    assertThrows(UnsupportedOperationException.class, () -> stack.clear());
  }

  @Test
  void iterator() {
    assertThrows(UnsupportedOperationException.class, () -> stack.iterator());
  }

  @Test
  void hashCodeTest() {
    assertThrows(UnsupportedOperationException.class, () -> stack.hashCode());
  }
}