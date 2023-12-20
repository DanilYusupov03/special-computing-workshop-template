package ru.spbu.apcyb.svp.tasks;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;



class Task1Test {
    private Task1 ts;

    @BeforeEach
    public void initTask() {
        ts = new Task1();
    }

    @Test
    void defaultComb() {
        Integer[] arr = {3, 2};
        List<String> combinations = new ArrayList<>();
        int actual = ts.getCombinations(5, 3, "", arr, combinations);
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    void defaultComb2() {
        Integer[] arr = {2, 1};
        List<String> combinations = new ArrayList<>();
        int actual = ts.getCombinations(4, 2, "", arr, combinations);
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    void reverseDefaultComb2() {
        Integer[] arr = {1, 2};
        List<String> combinations = new ArrayList<>();
        int actual = ts.getCombinations(4, 2, "", arr, combinations);
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    void singleComb() {
        Integer[] arr = {1};
        List<String> combinations = new ArrayList<>();
        int actual = ts.getCombinations(1000, 1, "", arr, combinations);
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    void defaultComb3() {
        Integer[] arr = {500, 1};
        List<String> combinations = new ArrayList<>();
        int actual = ts.getCombinations(1000, 500, "", arr, combinations);
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    void nonDefaultComb() {
        Integer[] arr = {10, 6};
        List<String> combinations = new ArrayList<>();
        int actual = ts.getCombinations(5, 10, "", arr, combinations);
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    void checkComb() {
        Integer[] arr = {1, 2, 3};
        List<String> combinations = new ArrayList<>();
        ts.getCombinations(6, 3, "", arr, combinations);
        ArrayList<String> expected = new ArrayList<>();
        expected.add(" 1  1  1  1  1  1 ");
        expected.add(" 2  1  1  1  1 ");
        expected.add(" 2  2  1  1 ");
        expected.add(" 2  2  2 ");
        expected.add(" 3  1  1  1 ");
        expected.add(" 3  2  1 ");
        expected.add(" 3  3 ");
        assertEquals(expected, ts.getCombinations(combinations));
    }

    @Test
    void checkComb2() {
        String arr = "3 2 5 9";
        List<String> combinations = new ArrayList<>();
        ts.getCombinations(12, 9, "", ts.parseDenomination(arr), combinations);
        ArrayList<String> expected = new ArrayList<>();
        expected.add(" 9  3 ");
        expected.add(" 5  5  2 ");
        expected.add(" 5  3  2  2 ");
        expected.add(" 3  3  3  3 ");
        expected.add(" 3  3  2  2  2 ");
        expected.add(" 2  2  2  2  2  2 ");
        assertEquals(expected, ts.getCombinations(combinations));
    }

    @Test
    void checkDuplicate() {
        String arr = "1 1";
        Integer[] parsedArr = ts.parseDenomination(arr);
        List<String> combinations = new ArrayList<>();
        int actual = ts.getCombinations(5, parsedArr[0], "", parsedArr, combinations);
        int expected = 1;
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a b", "", "3000000000", "1+1 2+1"})
    void checkDenomination(String arg) {
        assertThrows(IllegalArgumentException.class, () -> ts.parseDenomination(arg));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1 1", "0"})
    void checkDenominationSigned(String arg) {
        assertThrows(ArithmeticException.class, () -> ts.parseDenomination(arg));
    }

    @ParameterizedTest
    @ValueSource(strings = {"100 50 20", "100 20 50", "3 2", "1"})
    void checkDenomination2(String arg) {
        assertDoesNotThrow(() -> {
            ts.parseDenomination(arg);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"asd", "", "3000000000", "3+2", "1 1 1"})
    void checkSum(String arg) {
        assertThrows(IllegalArgumentException.class, () -> ts.parseSum(arg));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-5", "0"})
    void checkSumSigned(String arg) {
        assertThrows(ArithmeticException.class, () -> ts.parseSum(arg));
    }

    @ParameterizedTest
    @ValueSource(strings = {"100", "1", "1000"})
    void checkSum2(String arg) {
        assertDoesNotThrow(() -> {
            ts.parseSum(arg);
        });
    }
}

