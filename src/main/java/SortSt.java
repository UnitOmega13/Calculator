package com.company;

import java.util.*;

class SortSt {
	private static String expression;
	private static String leftBracket;

	private static String sortingStation(String expression, String leftBracket) {
		SortSt.expression = expression;
		SortSt.leftBracket = leftBracket;
		if (expression == null || expression.length() == 0)
			throw new IllegalStateException("Expression isn't specified.");
		if (Main.MAIN_MATH_OPERATIONS == null || Main.MAIN_MATH_OPERATIONS.isEmpty())
			throw new IllegalStateException("Operations aren't specified.");

		List<String> out = new ArrayList<>();

		Stack<String> stack = new Stack<>();

		Set<String> operationSymbols = new HashSet<>(Main.MAIN_MATH_OPERATIONS.keySet());
		operationSymbols.add(leftBracket);
		operationSymbols.add(")");

		int index = 0;

		boolean findNext = true;
		while (findNext) {
			int nextOperationIndex = expression.length();
			String nextOperation = "";

			for (String operation : operationSymbols) {
				int i = expression.indexOf(operation, index);
				if (i >= 0 && i < nextOperationIndex) {
					nextOperation = operation;
					nextOperationIndex = i;
				}
			}

			if (nextOperationIndex == expression.length()) {
				findNext = false;
			} else {

				if (index != nextOperationIndex) {
					out.add(expression.substring(index, nextOperationIndex));
				}

				if (nextOperation.equals(leftBracket)) {
					stack.push(nextOperation);
				}

				else if (nextOperation.equals(")")) {
					while (!stack.peek().equals(leftBracket)) {
						out.add(stack.pop());
						if (stack.empty()) {
							throw new IllegalArgumentException("Unmatched brackets");
						}
					}
					stack.pop();
				}

				else {
					while (!stack.empty() && !stack.peek().equals(leftBracket) &&
							(Main.MAIN_MATH_OPERATIONS.get(nextOperation) >= Main.MAIN_MATH_OPERATIONS.get(stack.peek()))) {
						out.add(stack.pop());
					}
					stack.push(nextOperation);
				}
				index = nextOperationIndex + nextOperation.length();
			}
		}

		if (index != expression.length()) {
			out.add(expression.substring(index));
		}

		while (!stack.empty()) {
			out.add(stack.pop());
		}
		StringBuilder result = new StringBuilder();
		if (!out.isEmpty())
			result.append(out.remove(0));
		while (!out.isEmpty())
			result.append(" ").append(out.remove(0));

		return result.toString();
	}

	static String sortingStation(String expression) {
		return sortingStation(expression, "(");
	}
}
