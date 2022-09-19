package Calculator.Worker;

import Calculator.Args;
import Calculator.Context;
import Calculator.Exeptions.Stack.EmptyStackExeption;

public class Minus implements Worker{
    public void execute(Args arg, Context context) throws EmptyStackExeption {
        if (context.stack.empty())
            throw new EmptyStackExeption("Stack is empty");
        double a = context.stack.pop();

        if (context.stack.empty())
            throw new EmptyStackExeption("Stack is empty for second argument");
        double b = context.stack.pop();

        context.stack.push(a - b);
    }
}
