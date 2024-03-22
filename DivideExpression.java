package Calculator;

public class DivideExpression implements Expression {
    private final Expression leftExpression;
    private final Expression rightExpression;

    public DivideExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public void interpret(Context context) {
        leftExpression.interpret(context);
        int leftValue = context.getOutput();
        rightExpression.interpret(context);
        int rightValue = context.getOutput();
        if (rightValue != 0) {
            context.setOutput(leftValue / rightValue);
        } else {
            throw new ArithmeticException("Division by zero");
        }
    }
}
