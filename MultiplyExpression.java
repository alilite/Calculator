package Calculator;

public class MultiplyExpression implements Expression {
    private final Expression leftExpression;
    private final Expression rightExpression;

    public MultiplyExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public void interpret(Context context) {
        leftExpression.interpret(context);
        int leftValue = context.getOutput();
        rightExpression.interpret(context);
        int rightValue = context.getOutput();
        context.setOutput(leftValue * rightValue);
    }
}
