package Calculator.Exeptions.Arifmetics;

import Calculator.Exeptions.Arifmetics.Arifmetics;

public class SqrtofMinusNumExeption extends Arifmetics {
    public SqrtofMinusNumExeption(String message, double num) {


        super(message + Double.toString(num));
    }
}

