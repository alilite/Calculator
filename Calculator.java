package Calculator;

import java.util.Stack;

public class Calculator {
    private final Interpreter interpreter;

    public Calculator(Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    public int evaluate(String expression) {
        Stack<Expression> stack = new Stack<>();
        Context context = new Context(expression);

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            Expression currentExpression = interpreter.getExpression(c);

            if (currentExpression != null) {
                if (currentExpression instanceof EqualExpression) {
                    break; // Stop parsing on encountering '='
                }
                if (currentExpression instanceof DecimalExpression) {
                    continue; // Skip decimal points
                }

                if (currentExpression instanceof OpenParenthesisExpression) {
                    // Evaluate expression within parentheses and push to stack
                    StringBuilder nestedExpression = new StringBuilder();
                    for (int j = i + 1; j < expression.length(); j++) {
                        if (expression.charAt(j) == ')') {
                            i = j; // Update index
                            break;
                        }
                        nestedExpression.append(expression.charAt(j));
                    }
                    stack.push(new NumberExpression(evaluate(nestedExpression.toString())));
                    continue;
                }

                // Pop two numbers from stack and apply the operation
                Expression leftExpression = stack.isEmpty() ? null : stack.pop();
                Expression rightExpression = stack.isEmpty() ? null : stack.pop();
                if (leftExpression != null && rightExpression != null) {
                    if (currentExpression instanceof EqualExpression) {
                        break;
                    }
                    currentExpression = new AddExpression(leftExpression, rightExpression);
                }
                stack.push(currentExpression);
            }
        }

        if (!stack.isEmpty()) {
            stack.pop().interpret(context);
        }
        return context.getOutput();
    }

    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();
        Calculator calculator = new Calculator(interpreter);
        
        // Test the calculator
        String expression1 = "2 + 3 * 5 ="; // Should output 25
        String expression2 = "(4 + 2) / 2 ="; // Should output 3
        String expression3 = "10 / (2 + 3) ="; // Should output 2
        
        System.out.println(expression1 + " Result: " + calculator.evaluate(expression1));
        System.out.println(expression2 + " Result: " + calculator.evaluate(expression2));
        System.out.println(expression3 + " Result: " + calculator.evaluate(expression3));
    }
}
