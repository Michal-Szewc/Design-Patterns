package DP;

public class CalculateVisitor implements Visitor{
    double[][] value;
    double scalar_value = 0;

    boolean calculated = false;
    boolean last_scalar = true;

    @Override
    public void visit(Add add) throws Exception {
        add.a.accept(this);
        double[][] value1 = value.clone();
        add.b.accept(this);
        double[][] value2 = value.clone();
        value = MatrixCalculations.add(value1, value2);

        calculated = true;
        last_scalar = false;
    }

    @Override
    public void visit(Subtract sub) throws Exception {
        sub.a.accept(this);
        double[][] value1 = value.clone();
        sub.b.accept(this);
        double[][] value2 = value.clone();
        value = MatrixCalculations.subtract(value1, value2);

        calculated = true;
        last_scalar = false;
    }

    @Override
    public void visit(Multiply mul) throws Exception {
        mul.a.accept(this);
        double[][] value1 = value.clone();
        mul.b.accept(this);
        double[][] value2 = value.clone();
        value = MatrixCalculations.multiply(value1, value2);

        calculated = true;
        last_scalar = false;
    }

    @Override
    public void visit(Scale s) throws Exception {
        s.a.accept(this);
        s.s.accept(this);
        value = MatrixCalculations.multiply(value, scalar_value);

        calculated = true;
        last_scalar = false;
    }

    @Override
    public void visit(Matrix m) throws Exception {
        if(m.initialized) {
            value = m.tab;
        }
        else if (m.parent == m){
            throw new Exception("The matrix " + m.name + " was not initialized");
        }
        else {
            m.parent.accept(this);
            calculated = true;
            last_scalar = false;
        }
    }

    @Override
    public void visit(Scalar s) throws Exception {
        if(s.initialized) {
            scalar_value = s.val;
        }
        else {
            s.parent.accept(this);
            calculated = true;
            last_scalar = true;
        }
    }

    @Override
    public void visit(Inversion inv) throws Exception {
        inv.m.accept(this);
        value = MatrixCalculations.inverse(value);

        calculated = true;
        last_scalar = false;
    }

    @Override
    public void visit(Det det) throws Exception {
        det.m.accept(this);
        scalar_value = MatrixCalculations.determinant(value);

        calculated = true;
        last_scalar = true;
    }

    public double[][] getValue()  throws Exception{
        if(!calculated)
            throw new Exception("there was nothing to calculate");
        if(last_scalar) {
            value = new double[1][1];
            value[0][0] = scalar_value;
        }
        return value;
    }
}
