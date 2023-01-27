package DP;

public class Det implements Element{

    Matrix m;

    Det(Matrix _m){
        m = _m;
    }

    @Override
    public void accept(Visitor v) throws Exception{
        v.visit(this);
    }
}
