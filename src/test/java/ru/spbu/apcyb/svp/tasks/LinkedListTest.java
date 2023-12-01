package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class LinkedListTest {

  @Test
  void addObjectTest() {
    LinkedList list = new LinkedList();
    list.add(4);
    Assertions.assertArrayEquals(new Integer[]{4}, list.toArray());
  }

  @Test
  void addSeveralObjectsTest() {
    LinkedList list = new LinkedList();
    list.add(0, 4);
    list.add(0, "foo bar");
    list.add(1, 7);
    Assertions.assertArrayEquals(new Object[]{"foo bar", 7, 4}, list.toArray());
  }

  @Test
  void addExceptionTest() {
    LinkedList list = new LinkedList();
    list.add("foo bar");
    assertThrows(IndexOutOfBoundsException.class,
        () -> list.add(3, 7));
  }

  @Test
  void sizeEmptyTest() {
    LinkedList list = new LinkedList();
    Assertions.assertEquals(0, list.size());
  }

  @Test
  void multipleSizeTest() {
    LinkedList list = new LinkedList();
    list.add("foo bar");
    list.add(0, "baz baz");
    list.remove(1);
    Assertions.assertEquals(1, list.size());
  }

  @Test
  void emptyListTest() {
    LinkedList list = new LinkedList();
    Assertions.assertTrue(list.isEmpty());
  }

  @Test
  void notEmptyListTest() {
    LinkedList list = new LinkedList();
    list.add(new int[]{4});
    Assertions.assertFalse(list.isEmpty());
  }

  @Test
  void removeSeveralIndexTest() {
    LinkedList list = new LinkedList();
    list.add(4);
    Object element = list.remove(0);

    assertAll(
        () -> Assertions.assertTrue(list.isEmpty()),
        () -> Assertions.assertEquals("4", element.toString())
    );
  }

  @Test
  void removeFromEmptyListTest() {
    LinkedList list = new LinkedList();
    assertThrows(IndexOutOfBoundsException.class,
        () -> list.remove(0));
  }

  @Test
  void getTest() {
    LinkedList list = new LinkedList();
    list.add(4);
    list.add("foo bar");
    list.add(new int[]{4});
    Assertions.assertEquals("foo bar", list.get(1));
  }

  @Test
  void getExceptionTest() {
    LinkedList list = new LinkedList();
    list.add(4);
    assertThrows(IndexOutOfBoundsException.class,
        () -> list.get(2));
  }

  @Test
  void indexOfTrueTest() {
    LinkedList list = new LinkedList();
    list.add("foo bar");
    Assertions.assertEquals(0, list.indexOf("foo bar"));
  }

  @Test
  void indexOfFalseTest2() {
    LinkedList list = new LinkedList();
    list.add("foo bar");
    Assertions.assertEquals(-1, list.indexOf(10));
  }

  @Test
  void clearExceptionTest() {
    LinkedList list = new LinkedList();
    assertThrows(UnsupportedOperationException.class, list::clear);
  }

  @Test
  void containsExceptionTest() {
    LinkedList list = new LinkedList();
    assertThrows(UnsupportedOperationException.class, () -> list.contains(2));
  }

  @Test
  void iteratorExceptionTest() {
    LinkedList list = new LinkedList();
    assertThrows(UnsupportedOperationException.class, list::iterator);
  }

  @Test
  void setExceptionTest() {
    LinkedList list = new LinkedList();
    assertThrows(UnsupportedOperationException.class, () -> list.set(2, 4));
  }

  @Test
  void lastIndexOfExceptionTest() {
    LinkedList list = new LinkedList();
    assertThrows(UnsupportedOperationException.class, () -> list.lastIndexOf(2));
  }

  @Test
  void listIteratorExceptionTest() {
    LinkedList list = new LinkedList();
    assertThrows(UnsupportedOperationException.class, list::listIterator);
  }

  @Test
  void sublistExceptionTest() {
    LinkedList list = new LinkedList();
    assertThrows(UnsupportedOperationException.class, () -> list.subList(2, 4));
  }
}