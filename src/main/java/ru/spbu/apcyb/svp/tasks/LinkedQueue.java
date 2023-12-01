package ru.spbu.apcyb.svp.tasks;


import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Realization of a queue with interface implementation java.util.Queue.
 */
public class LinkedQueue implements java.util.Queue<Object> {

  LinkedList list = new LinkedList();

  @Override
  public boolean add(Object o) throws IllegalStateException {
    list.add(o);
    return true;
  }

  @Override
  public Object peek() {
    if (list.isEmpty()) {
      return null;
    }
    return list.get(0);
  }

  @Override
  public Object poll() {
    if (list.isEmpty()) {
      return null;
    }
    return list.remove(0);
  }

  @Override
  public Object element() throws NoSuchElementException {
    if (list.isEmpty()) {
      throw new NoSuchElementException();
    }
    return list.get(0);
  }

  @Override
  public boolean isEmpty() {
    return list.isEmpty();
  }

  @Override
  public int size() {
    return list.size();
  }

  @Override
  public Object[] toArray() {
    return list.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method toArray(T[]) is not defined");
  }

  @Override
  public boolean offer(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method offer is not defined");
  }

  @Override
  public boolean contains(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method contains is not defined");
  }

  @Override
  public Iterator<Object> iterator() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method iterator is not defined");
  }

  @Override
  public Object remove() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method remove is not defined");
  }

  @Override
  public boolean remove(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method remove is not defined");
  }

  @Override
  public boolean addAll(Collection c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method addAll is not defined");
  }

  @Override
  public void clear() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method clear is not defined");
  }

  @Override
  public boolean retainAll(Collection c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method retainAll is not defined");
  }

  @Override
  public boolean removeAll(Collection c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method removeAll is not defined");
  }

  @Override
  public boolean containsAll(Collection c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method containsAll is not defined");
  }
}