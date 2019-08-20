package com.company;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Main {
    private static String leftBracket;

    public static void main(String[] args) {
        String expression;
        String expressionconverted = "";
        String[] romanExpression;
        int[] numbers = new int[900000];
        String[] operators;
        Scanner scanner = new Scanner(System.in);
        if(!scanner.hasNextInt()){
            while (scanner.hasNext()) {
                expression = scanner.next().trim().toUpperCase();
                romanExpression = expression.split("[+-/*]");
                operators = expression.split("[a-z]+|[A-Z]+");
                for(int i = 0; i <= operators.length - 1; i ++) {
                    numbers[i] = RomanToDecimal.convert(romanExpression[i]);
                    if (operators[i].compareTo("+") == 0) {
                        expressionconverted = expressionconverted + numbers[i-1] + "+";
                    }
                    if (operators[i].compareTo("-") == 0) {
                        expressionconverted = expressionconverted + numbers[i-1] + "-";
                    }
                    if (operators[i].compareTo("/") == 0) {
                        expressionconverted = expressionconverted + numbers[i-1] + "/";
                    }
                    if (operators[i].compareTo("*") == 0) {
                        expressionconverted = expressionconverted + numbers[i-1] + "*";
                    }
                    if (i == operators.length - 1) {
                        expressionconverted = expressionconverted + numbers[i];
                    }
                }
                if (expression.equals("exit")) {
                    break;
                }
                System.out.println("Введенное выражение " + expression);
                System.out.println("Конвертированное выражение " + expressionconverted);
                System.out.println("Результат " + calculateExpression(expressionconverted));
            }
        }else {
            while (scanner.hasNext()) {
                expression = scanner.next().trim();
                if (expression.equals("exit")) {
                    break;
                }
                System.out.println(expression);
                System.out.println("Результат " + calculateExpression(expression));
            }
        }
    }


    public static final Map<String, Integer> MAIN_MATH_OPERATIONS;

    static {
        MAIN_MATH_OPERATIONS = new HashMap<>();
        MAIN_MATH_OPERATIONS.put("*", 1);
        MAIN_MATH_OPERATIONS.put("/", 1);
        MAIN_MATH_OPERATIONS.put("+", 2);
        MAIN_MATH_OPERATIONS.put("-", 2);
    }


    public static BigDecimal calculateExpression(String expression) {
        String rpn = SortSt.sortingStation(expression);
        StringTokenizer stringTokenizer = new StringTokenizer(rpn, " ");
        Stack<BigDecimal> stack = new Stack<>();
        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            if (!MAIN_MATH_OPERATIONS.keySet().contains(token)) {
                stack.push(new BigDecimal(token));
            } else {
                BigDecimal operand2 = stack.pop();
                BigDecimal operand1 = stack.empty() ? BigDecimal.ZERO : stack.pop();
                if (token.equals("*")) {
                    stack.push(operand1.multiply(operand2));
                } else if (token.equals("/")) {
                    stack.push(operand1.divide(operand2, 2, RoundingMode.HALF_UP));
                } else if (token.equals("+")) {
                    stack.push(operand1.add(operand2));
                } else if (token.equals("-")) {
                    stack.push(operand1.subtract(operand2));
                }
            }
        }
        if (stack.size() != 1)
            throw new IllegalArgumentException("Expression syntax error.");
        return stack.pop();
    }

    private Main() {
    }
}

