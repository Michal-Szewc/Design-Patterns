package DP;

public class Multiply implements Element{

    Matrix a;
    Matrix b;

    Multiply(Matrix _a, Matrix _b){
        a = _a;
        b = _b;
    }

    @Override
    public void accept(Visitor v)  throws Exception{
        v.visit(this);
    }
}
