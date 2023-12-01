package ru.spbu.apcyb.svp.tasks;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Realization of a doubly linked list with interface implementation java.util.List.
 */
public class LinkedList implements java.util.List<Object> {

  private int size = 0;
  private Node head = null;

  /**
   * Doubly linked list nodes.
   */
  public static class Node {


    private final Object data;
    private Node right;
    private Node left;

    Node(Object data, Node left, Node right) {
      this.data = data;
      this.right = right;
      this.left = left;
    }
  }

  @Override
  public boolean add(Object o) {
    Node current = head;
    if (head == null) {
      head = new Node(o, null, null);
    } else {
      while (current.right != null) {
        current = current.right;
      }
      current.right = new Node(o, current, null);
    }
    size++;
    return true;
  }

  @Override
  public void add(int index, Object element) throws IndexOutOfBoundsException {
    Node current = head;
    if (index < 0 || index > this.size()) {
      throw new IndexOutOfBoundsException();
    }

    int curIndex = 0;
    while (curIndex != index) {
      current = current.right;
      curIndex++;
    }

    if (current == null) {
      head = new Node(element, null, null);
    } else if (current.left == null) {
      Node newNode = new Node(element, null, current);
      head.left = newNode;
      head = newNode;
    } else {
      Node newNode = new Node(element, current.left, current);
      newNode.left.right = newNode;
      current.left = newNode;
    }
    size++;
  }

  @Override
  public Object remove(int index) throws IndexOutOfBoundsException {
    Node current = head;
    if (index < 0 || index >= this.size()) {
      throw new IndexOutOfBoundsException();
    }
    int curIndex = 0;
    while (curIndex != index) {
      current = current.right;
      curIndex++;
    }
    if (current.left == null) {
      head = current.right;
    } else {
      current.left.right = current.right;
    }
    if (current.right != null) {
      current.right.left = current.left;
    }
    size--;
    return current.data;
  }

  @Override
  public boolean remove(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method remove is not defined");
  }

  @Override
  public Object get(int index) throws IndexOutOfBoundsException {
    Node current = head;
    if (index >= this.size() || index < 0) {
      throw new IndexOutOfBoundsException();
    }
    int curIndex = 0;
    while (curIndex != index) {
      current = current.right;
      curIndex++;
    }
    return current.data;
  }

  @Override
  public boolean isEmpty() {
    return head == null;
  }

  @Override
  public int indexOf(Object o) {

    Node current = this.head;
    int index = 0;
    while (current != null && current.data != o) {
      current = current.right;
      index++;
    }
    if (current != null) {
      return index;
    }
    return -1;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Object[] toArray() {
    Object[] result = new Object[this.size()];
    Node current = head;
    for (int i = 0; i < this.size(); i++) {
      result[i] = current.data;
      current = current.right;
    }
    return result;
  }

  @Override
  public <T> T[] toArray(T[] a) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method toArray(T[]) is not defined");
  }

  @Override
  public boolean contains(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method contains is not defined.");
  }

  @Override
  public Iterator<Object> iterator() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method iterator is not defined");
  }

  @Override
  public boolean containsAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method containsAll is not defined");
  }

  @Override
  public boolean addAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method addAll is not defined");
  }

  @Override
  public boolean addAll(int index, Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method addAll is not defined");
  }

  @Override
  public boolean removeAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method removeAll is not defined");
  }

  @Override
  public boolean retainAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method retainAll is not defined");
  }

  @Override
  public void clear() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method clear is not defined");
  }

  @Override
  public Object set(int index, Object element) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method set is not defined");
  }

  @Override
  public int lastIndexOf(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method lastIndexOf is not defined");
  }

  @Override
  public ListIterator<Object> listIterator() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method listIterator is not defined");
  }

  @Override
  public ListIterator<Object> listIterator(int index) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method listIterator is not defined");
  }

  @Override
  public List<Object> subList(int fromIndex, int toIndex) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method subList is not defined");
  }
}
