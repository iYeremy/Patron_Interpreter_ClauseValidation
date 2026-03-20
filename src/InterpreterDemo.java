import expressions.AndExpression;
import expressions.Expression;
import expressions.OrExpression;
import expressions.NotExpression;
import expressions.TerminalExpression;

public class InterpreterDemo {

    // subject: arrendatario OR inquilino
    public static Expression getSubjectExpression() {
        Expression lessee = new TerminalExpression("Arrendatario");
        Expression tenant = new TerminalExpression("Inquilino");
        return new OrExpression(lessee, tenant);
    }

    // action: pagara OR abonara
    public static Expression getActionExpression() {
        Expression pay = new TerminalExpression("pagara");
        Expression deposit = new TerminalExpression("abonara");
        return new OrExpression(pay, deposit);
    }

    // complement 1 (Object): renta OR canon
    public static Expression getObjectExpression() {
        Expression rent = new TerminalExpression("renta");
        Expression fee = new TerminalExpression("canon");
        return new OrExpression(rent, fee);
    }

    // complement 2 (Time): mensual OR anticipado
    public static Expression getTimeExpression() {
        Expression monthly = new TerminalExpression("mensual");
        Expression upfront = new TerminalExpression("anticipado");
        return new OrExpression(monthly, upfront);
    }

    // final real 100% real Subject AND Action AND Object AND Time AND NOT Forbidden
    public static Expression getLeaseValidationRule() {
        // Combine mandatory fields``
        Expression mandatory = new AndExpression(
            new AndExpression(getSubjectExpression(), getActionExpression()),
            new AndExpression(getObjectExpression(), getTimeExpression())
        );

        // Add the NOT restriction for "subarrendar"
        Expression forbidden = new NotExpression(new TerminalExpression("subarrendar"));

        return new AndExpression(mandatory, forbidden);
    }

    public static void main(String[] args) {
        Expression validator = getLeaseValidationRule();

        // Test context
        String validClause = "El Arrendatario pagara el canon mensual";
        String invalidClause = "El Inquilino pagara la renta mensual pero puede subarrendar";

        System.out.println("Test 1 (Valid): " + validator.interpret(validClause)); // muestra true
        System.out.println("Test 2 (Invalid - forbidden word): " + validator.interpret(invalidClause)); // muestra false
    }
}
