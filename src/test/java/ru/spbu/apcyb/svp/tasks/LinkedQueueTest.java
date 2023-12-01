package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LinkedQueueTest {

  @Test
  void addObjectTest() {
    LinkedQueue linkedQueue = new LinkedQueue();
    linkedQueue.add("foo bar");
    Assertions.assertEquals("foo bar", linkedQueue.peek());
  }

  @Test
  void sizeEmptyTest() {
    LinkedQueue linkedQueue = new LinkedQueue();
    Assertions.assertEquals(0, linkedQueue.size());
  }

  @Test
  void sizeSeveralTest() {
    LinkedQueue linkedQueue = new LinkedQueue();
    linkedQueue.add(4);
    linkedQueue.add(7);
    Assertions.assertEquals(2, linkedQueue.size());
  }

  @Test
  void pollTest() {
    LinkedQueue linkedQueue = new LinkedQueue();
    linkedQueue.add("foo bar");
    linkedQueue.add(4);
    Object element = linkedQueue.poll();
    assertAll(
        () -> Assertions.assertEquals("foo bar", element),
        () -> Assertions.assertArrayEquals(new Object[]{4}, linkedQueue.toArray())
    );
  }

  @Test
  void pollNullTest() {
    LinkedQueue linkedQueue = new LinkedQueue();
    Assertions.assertNull(linkedQueue.poll());
  }

  @Test
  void elementTest() {
    LinkedQueue linkedQueue = new LinkedQueue();
    linkedQueue.add("foo bar");
    linkedQueue.add(4);
    Object element = linkedQueue.element();
    assertAll(
        () -> Assertions.assertEquals("foo bar", element),
        () -> Assertions.assertArrayEquals(new Object[]{"foo bar", 4}, linkedQueue.toArray())
    );
  }

  @Test
  void elementExceptionTest() {
    LinkedQueue linkedQueue = new LinkedQueue();
    Assertions.assertThrows(NoSuchElementException.class, linkedQueue::element);
  }

  @Test
  void peekTest() {
    LinkedQueue linkedQueue = new LinkedQueue();
    linkedQueue.add("foo bar");
    linkedQueue.add(4);
    Object element = linkedQueue.peek();
    assertAll(
        () -> Assertions.assertEquals("foo bar", element),
        () -> Assertions.assertArrayEquals(new Object[]{"foo bar", 4}, linkedQueue.toArray())
    );
  }

  @Test
  void peekNullTest() {
    LinkedQueue linkedQueue = new LinkedQueue();
    Assertions.assertNull(linkedQueue.peek());
  }

  @Test
  void isEmptyTest() {
    LinkedQueue linkedQueue = new LinkedQueue();
    assertTrue(linkedQueue.isEmpty());
  }

  @Test
  void isEmptyFalseTest() {
    LinkedQueue linkedQueue = new LinkedQueue();
    linkedQueue.add(4);
    assertFalse(linkedQueue.isEmpty());
  }

  @Test
  void clearTest() {
    LinkedQueue linkedQueue = new LinkedQueue();
    Assertions.assertThrows(UnsupportedOperationException.class, linkedQueue::clear);
  }

  @Test
  void offerTest() {
    LinkedQueue linkedQueue = new LinkedQueue();
    Assertions.assertThrows(UnsupportedOperationException.class, () -> linkedQueue.offer(4));
  }

  @Test
  void iteratorTest() {
    LinkedQueue linkedQueue = new LinkedQueue();
    Assertions.assertThrows(UnsupportedOperationException.class, linkedQueue::iterator);
  }

  @Test
  void containsTest() {
    LinkedQueue linkedQueue = new LinkedQueue();
    Assertions.assertThrows(UnsupportedOperationException.class, () -> linkedQueue.contains(4));
  }
}