package expressions;

public class TerminalExpression implements Expression {
    private final String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        // Verifica si el rol está presente en el contexto proporcionado
        return context.contains(data);
    }
}