package DP;

public class Add implements Element {
    Matrix a;
    Matrix b;

    Add(Matrix _a, Matrix _b){
        a = _a;
        b = _b;
    }

    @Override
    public void accept(Visitor v)  throws Exception{
        v.visit(this);
    }
}
