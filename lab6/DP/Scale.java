package DP;

public class Scale implements Element{

    Matrix a;
    Scalar s;

    Scale(Matrix _a, Scalar _s){
        a = _a;
        s = _s;
    }

    @Override
    public void accept(Visitor v)  throws Exception{
        v.visit(this);
    }
}
