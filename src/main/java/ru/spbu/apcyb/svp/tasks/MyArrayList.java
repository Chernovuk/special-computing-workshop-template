package ru.spbu.apcyb.svp.tasks;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;


/**
 * Task 2.
 */
public class MyArrayList implements List<Object> {

  private Object[] data;
  private int size;
  private static final int DEFAULT_CAPACITY = 10;

  public static final Logger logger = Logger.getLogger(MyArrayList.class.getName());

  public MyArrayList() {
    data = new Object[DEFAULT_CAPACITY];
    size = 0;
  }

  /**
   * Increasing capacity of data.
   */
  private void resize() {
    Object[] newData;
    if (data.length == 0) {
      newData = new Object[DEFAULT_CAPACITY];
    } else if (data.length <= Integer.MAX_VALUE / 2) {
      newData = new Object[data.length * 2];
    } else if (data.length == Integer.MAX_VALUE) {
      throw new IndexOutOfBoundsException("The list already has maximum available size!");
    } else {
      newData = new Object[Integer.MAX_VALUE];
    }

    System.arraycopy(data, 0, newData, 0, data.length);
    data = newData;
  }

  /**
   * Adding element to the end of list.
   *
   * @param element element which is to be added
   * @return true if operation was successful
   * @throws NullPointerException if element is null
   */
  @Override
  public boolean add(Object element) {
    if (element == null) {
      throw new NullPointerException("The element is null!");
    }
    try {
      if (size == data.length) {
        resize();
      }
      data[size] = element;
      ++size;
      return true;
    } catch (IndexOutOfBoundsException exception) {
      logger.info(exception.getMessage());
    }
    return false;
  }

  /**
   * Inserting element in the list at given index.
   *
   * @param index index at which the specified element is to be inserted
   * @param element element to be inserted
   * @throws IndexOutOfBoundsException if index is greater than size or less than zero
   * @throws NullPointerException if element is null
   */
  @Override
  public void add(int index, Object element) {
    if (index > size || index < 0) {
      throw new IndexOutOfBoundsException("Index is out of bounds!");
    }
    if (element == null) {
      throw new NullPointerException("The element is null!");
    }

    try {
      if (size == data.length) {
        resize();
      }
      java.util.ArrayList<Object> tempList = new java.util.ArrayList<>(Arrays.asList(data));
      tempList.add(index, element);
      data = tempList.toArray();
      ++size;
    } catch (IndexOutOfBoundsException exception) {
      logger.info(exception.getMessage());
    }
  }

  /**
   * Deleting element by index.
   *
   * @param index the index of the element to be removed
   * @return deleted element
   * @throws IndexOutOfBoundsException if index is greater than size or less than a zero
   */
  @Override
  public Object remove(int index) {
    if (index >= size || index < 0) {
      throw new IndexOutOfBoundsException("Index is out of bounds!");
    }
    Object temp = data[index];
    System.arraycopy(data, index + 1, data, index, size - index);
    --size;
    return temp;
  }

  @Override
  public boolean remove(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This operation isn't implemented yet.");
  }

  /**
   * Checking presence of an element.
   *
   * @param value element whose presence in this list is to be tested
   * @return true if the element is in the list
   */
  @Override
  public boolean contains(Object value) {
    for (Object obj : data) {
      if (obj == value) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checking if list is empty.
   *
   * @return true if the list doesn't contain any elements
   */
  @Override
  public boolean isEmpty() {
    return (size == 0);
  }

  /**
   * Getting an element by index.
   *
   * @param index index of the element to return
   * @return element in the list at index position
   * @throws IndexOutOfBoundsException if index is greater than size or less than zero
   */
  @Override
  public Object get(int index) {
    if (index >= size || index < 0) {
      throw new IndexOutOfBoundsException("Index is out of bounds!");
    }
    return data[index];
  }

  @Override
  public String toString() {
    if (size == 0) {
      return "[]";
    }

    StringBuilder str = new StringBuilder("[");

    for (int i = 0; i < size - 1; ++i) {
      str.append(data[i]).append(", ");
    }
    str.append(data[size - 1]).append("]");
    return str.toString();
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean addAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This operation isn't implemented yet.");
  }

  @Override
  public boolean addAll(int index, Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This operation isn't implemented yet.");
  }

  @Override
  public void clear() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This operation isn't implemented yet.");
  }

  @Override
  public boolean containsAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This operation isn't implemented yet.");
  }

  @Override
  public int indexOf(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This operation isn't implemented yet.");
  }

  @Override
  public Iterator<Object> iterator() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This operation isn't implemented yet.");
  }

  @Override
  public int lastIndexOf(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This operation isn't implemented yet.");
  }

  @Override
  public ListIterator<Object> listIterator() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This operation isn't implemented yet.");
  }

  @Override
  public ListIterator<Object> listIterator(int index) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This operation isn't implemented yet.");
  }

  @Override
  public boolean removeAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This operation isn't implemented yet.");
  }

  @Override
  public boolean retainAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This operation isn't implemented yet.");
  }

  @Override
  public Object set(int index, Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This operation isn't implemented yet.");
  }

  @Override
  public List<Object> subList(int fromIndex, int toIndex) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This operation isn't implemented yet.");
  }

  @Override
  public <T> T[] toArray(T[] a) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This operation isn't implemented yet.");
  }

  @Override
  public Object[] toArray() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This operation isn't implemented yet.");
  }
}