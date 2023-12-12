package ru.spbu.apcyb.svp.tasks;

import java.io.Serializable;
import java.util.Collection;
import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * Task 2.
 */
public class MyStack extends java.util.Stack<Object> implements Serializable {

  private final transient MyArrayList stack;

  public MyStack() {
    stack = new MyArrayList();
  }


  /**
   * Pushing an item on the top of a stack.
   *
   * @param item the item to be pushed onto this stack.
   * @return pushed item
   */
  @Override
  public Object push(Object item) {
    stack.add(item);
    return item;
  }

  /**
   * Deleting item at the top of a stack.
   *
   * @return deleted item
   * @throws EmptyStackException if stack is empty
   */
  @Override
  public synchronized Object pop() {
    try {
      return stack.remove(stack.size() - 1);
    } catch (IndexOutOfBoundsException exception) {
      throw new EmptyStackException();
    }
  }

  /**
   * Checks item at the top of a stack.
   *
   * @return checked item
   * @throws EmptyStackException if stack is empty
   */
  @Override
  public synchronized Object peek() {
    try {
      return stack.get(stack.size() - 1);
    } catch (IndexOutOfBoundsException exception) {
      throw new EmptyStackException();
    }
  }

  /**
   * Checks whether stack is empty.
   *
   * @return true if there are no items in the stack
   */
  @Override
  public synchronized boolean isEmpty() {
    return stack.isEmpty();
  }

  @Override
  public synchronized String toString() {
    if (stack.isEmpty()) {
      return "[]";
    }
    StringBuilder str = new StringBuilder("[");
    for (int i = 0; i < stack.size() - 1; ++i) {
      str.append(stack.get(i)).append(", ");
    }
    str.append(stack.get(stack.size() - 1)).append("]");
    return str.toString();
  }

  @Override
  public synchronized boolean addAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override public synchronized void clear() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public synchronized boolean equals(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override public synchronized Iterator<Object> iterator() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public synchronized int hashCode() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public synchronized int search(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override public synchronized boolean remove(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override public synchronized Object remove(int index) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }
}
