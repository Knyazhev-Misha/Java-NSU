package Calculator.Worker;

import Calculator.Args;
import Calculator.Context;
import Calculator.Exeptions.Stack.EmptyStackExeption;

public class Pop implements Worker{
    public void execute(Args arg, Context context) throws EmptyStackExeption {
        if (context.stack.empty())
            throw new EmptyStackExeption("Stack is empty");

        context.stack.pop();
    }
}
