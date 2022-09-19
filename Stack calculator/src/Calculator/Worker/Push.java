package Calculator.Worker;

import Calculator.Args;
import Calculator.Context;
import Calculator.Exeptions.Args.NullArgsExeption;

public class Push implements Worker{
    public void execute(Args arg, Context context) throws NullArgsExeption {
        if (context.defineBuffer.get(arg.arg_1) == null)
        throw new NullArgsExeption("Null arguments");

        double a = context.defineBuffer.get(arg.arg_1);

        context.stack.push(a);
    }
}
