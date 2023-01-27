package DP;

public class Inversion implements Element{

    Matrix m;

    Inversion(Matrix _m){
        m = _m;
    }

    @Override
    public void accept(Visitor v) throws Exception{
        v.visit(this);
    }
}
