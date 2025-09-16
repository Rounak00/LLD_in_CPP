import java.util.HashMap;
import java.util.Map;

// Context class
class Context {
    private Map<String, Integer> data = new HashMap<>();

    public void assign(String variable, int value) {
        data.put(variable, value);
    }

    public int getValue(String variable) {
        return data.get(variable);
    }
}

// Abstract Expression
interface Expression {
    int interpret(Context context);
}

// Terminal Expression (just a variable)
class TerminalExpression implements Expression {
    private String variable;

    public TerminalExpression(String variable) {
        this.variable = variable;
    }

    @Override
    public int interpret(Context context) {
        return context.getValue(variable);
    }
}

// NonTerminal Expression: Addition
class AddExpression implements Expression {
    private Expression left;
    private Expression right;

    public AddExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Context context) {
        return left.interpret(context) + right.interpret(context);
    }
}

// NonTerminal Expression: Subtraction
class SubtractExpression implements Expression {
    private Expression left;
    private Expression right;

    public SubtractExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Context context) {
        return left.interpret(context) - right.interpret(context);
    }
}

// Main class (Client)
public class InterpreterPatternDemo {
    public static void main(String[] args) {
        Context context = new Context();

        // Assign variables
        context.assign("a", 10);
        context.assign("b", 20);
        context.assign("c", 5);

        // Expression: (a + b) - c
        Expression expression = new SubtractExpression(
                new AddExpression(
                        new TerminalExpression("a"),
                        new TerminalExpression("b")
                ),
                new TerminalExpression("c")
        );

        System.out.println("(a + b) - c = " + expression.interpret(context));
    }
}
