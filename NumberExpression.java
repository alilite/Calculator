package Calculator;

public class NumberExpression implements Expression {
    private final int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    @Override
    public void interpret(Context context) {
        context.setOutput(number);
    }
}
