package ru.spbu.apcyb.svp.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;


/**
 * Task1 ATM.
 */
public class Task1 {

    private static final Logger logger = Logger.getLogger(Task1.class.getName());
    private final ArrayList<String> combinations = new ArrayList<>();

    /**
     * Method for obtaining the number of banknote combinations of a given amount.
     *
     * @param sum                  - variable to transfer the amount
     * @param prevDenomination     - variable to store the previous denomination
     * @param combination          - string for storing and outputting the combination
     * @param denominationArr      - array of denominations
     * @return                     - number of combinations
     */
    public int getCombinations(int sum, int prevDenomination, String combination, Integer[] denominationArr) {
        int count = 0;
        if (sum == 0) {
            count++;
            logger.info(combination);
            combinations.add(combination);
        }
        for (int curDenomination : denominationArr) {
            if ((prevDenomination >= curDenomination) && (sum >= curDenomination)) {
                count += getCombinations(sum - curDenomination, curDenomination, combination + " " + curDenomination + " ", denominationArr);
            }
        }
        return count;
    }

    /**
     * Method for parsing the amount.
     *
     * @param str - line with the entered amount
     * @return    - sum
     */
    public int parseSum(String str) {
        return parseToInt(str);
    }

    /**
     * Method for parsing denominations.
     *
     * @param str - line with denominations
     * @return    - sorted array of denominations
     */
    public Integer[] parseDenomination(String str) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Error! Empty string!");
        }
        String[] strMas = str.split(" ");
        Integer[] denominationArr = new Integer[strMas.length];
        for (int i = 0; i < denominationArr.length; i++) {
            denominationArr[i] = parseToInt(strMas[i]);
        }
        return Arrays.stream(denominationArr).sorted(Comparator.reverseOrder()).distinct().toArray(Integer[]::new);
    }

    /**
     * Method for parsing from String to Integer.
     *
     * @param str - number in String type
     * @return    - Integer type number
     */
    public int parseToInt(String str) {
        int value;
        boolean flag = false;
        try {
            value = Integer.parseInt(str);
            if (value <= 0) {
                flag = true;
                throw new ArithmeticException("Error!");
            }
        } catch (Exception exp) {
            if (flag) {
                throw new ArithmeticException("Error! Value <= 0");
            } else {
                throw new IllegalArgumentException("Error! Value is not integer");
            }
        }
        return value;
    }

    public List<String> getCombinations() {
        ArrayList<String> answer = new ArrayList<>(combinations);
        combinations.clear();
        return answer;
    }


    /**
     * The program accepts two lines from the console from the user.
     * sum - sum,
     * nominal - denominations separated by spaces
     * Displays combinations and their number.
     */
    public static void main(String[] args) {
        Task1 obj = new Task1();
        Logger logger = Logger.getLogger(obj.getClass().getName());
        Scanner sc = new Scanner(System.in);
        String sum = sc.nextLine();
        String nominal = sc.nextLine();
        String str = String.valueOf(obj.getCombinations(obj.parseSum(sum), obj.parseDenomination(nominal)[0], " ", obj.parseDenomination(nominal)));
        logger.info(str);
    }
}