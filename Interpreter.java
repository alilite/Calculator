package Calculator;

import java.util.HashMap;
import java.util.Map;

public class Interpreter {
    private final Map<Character, Expression> expressions = new HashMap<>();

    public Interpreter() {
        expressions.put('+', new AddExpression(null, null));
        expressions.put('-', new SubtractExpression(null, null));
        expressions.put('*', new MultiplyExpression(null, null));
        expressions.put('/', new DivideExpression(null, null));
        expressions.put('=', new EqualExpression());
        expressions.put('.', new DecimalExpression());
        expressions.put('(', new OpenParenthesisExpression());
        expressions.put(')', new CloseParenthesisExpression());
    }

    public Expression getExpression(char key) {
        return expressions.get(key);
    }
}
