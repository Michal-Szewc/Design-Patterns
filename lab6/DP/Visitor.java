package DP;

public interface Visitor {

    void visit(Add add) throws Exception;
    void visit(Subtract sub) throws Exception;
    void visit(Multiply mul) throws Exception;
    void visit(Scale s) throws Exception;
    void visit(Matrix m) throws Exception;
    void visit(Scalar s) throws Exception;
    void visit(Inversion inv) throws Exception;
    void visit(Det det) throws Exception;
}
