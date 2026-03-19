package expressions;

public class NotExpression implements Expression {
    private final Expression expr1;

    public NotExpression(Expression expr1) {
        this.expr1 = expr1;
    }

    @Override
    public boolean interpret(String context) {
        return !expr1.interpret(context);
    }
}