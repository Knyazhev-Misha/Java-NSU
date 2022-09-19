package Calculator.Worker;

import Calculator.Args;
import Calculator.Context;
import Calculator.Exeptions.Args.DefineException;
import Calculator.Exeptions.Args.NullArgsExeption;
import Calculator.Exeptions.Arifmetics.DivideZeroExeption;
import Calculator.Exeptions.Arifmetics.SqrtofMinusNumExeption;
import Calculator.Exeptions.Stack.EmptyStackExeption;

import java.util.zip.CheckedOutputStream;

public interface Worker {
    public void execute(Args arg, Context context) throws DivideZeroExeption, SqrtofMinusNumExeption, EmptyStackExeption, DefineException, NullArgsExeption;
}
