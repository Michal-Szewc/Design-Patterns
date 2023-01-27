package DP;

public class Graph {

    int addition_num = 0;
    int subtraction_num = 0;
    int multiplication_num = 0;
    int inversion_num = 0;
    int det_num = 0;
    public Matrix add(Matrix a, Matrix b) throws Exception {
        if(a.size_x != b.size_x || a.size_y != b.size_y)
            throw new Exception("Matrix dimensions should be the same to add them");
        Add addition = new Add(a, b);
        addition_num += 1;
        return new Matrix("add" + addition_num, a.size_x, a.size_y, addition);
    }

    public Matrix sub(Matrix a, Matrix b) throws Exception {
        if(a.size_x != b.size_x || a.size_y != b.size_y)
            throw new Exception("Matrix dimensions should be the same to subtract them");
        Subtract subtraction = new Subtract(a, b);
        subtraction_num += 1;
        return new Matrix("sub" + subtraction_num, a.size_x, a.size_y, subtraction);
    }

    public Matrix multiply(Matrix a, Matrix b) throws Exception {
        if(a.size_x != b.size_y)
            throw new Exception("First matrix's should have second dimension equal to second matrix's first dimension to multiply them (a.y == b.x)");
        Multiply multiplication = new Multiply(a, b);
        multiplication_num += 1;
        return new Matrix("mul" + multiplication_num, a.size_x, b.size_y, multiplication);
    }

    public Matrix multiply(Matrix a, Scalar s) throws Exception {
        Scale scale = new Scale(a, s);
        multiplication_num += 1;
        return new Matrix("mul" + multiplication_num, a.size_x, a.size_y, scale);
    }

    public Matrix multiply(Scalar s, Matrix a) throws Exception {
        Scale scale = new Scale(a, s);
        multiplication_num += 1;
        return new Matrix("mul" + multiplication_num, a.size_x, a.size_y, scale);
    }

    public Matrix inv(Matrix a) throws Exception {
        if(a.size_x != a.size_y)
            throw new Exception("Matrix should have equal dimensions to inverse it");
        Inversion inv = new Inversion(a);
        inversion_num += 1;
        return new Matrix("inv" + inversion_num, a.size_x, a.size_x, inv);
    }

    public Scalar det(Matrix a) throws Exception {
        if(a.size_x != a.size_y)
            throw new Exception("Matrix should have equal dimensions to calculate its determinant it");
        Det det = new Det(a);
        det_num += 1;
        return new Scalar("det" + det_num, det);
    }

    public void show(Element e) throws Exception{
        show(e, false);
    }
    public void show(Element e, boolean show_hidden_names) throws Exception {
        TextVisitor tv = new TextVisitor();
        tv.show_hidden_names(show_hidden_names);
        e.accept(tv);

        System.out.println(tv.getText());
    }

    public void calc(Element e) throws Exception {
        CalculateVisitor cv = new CalculateVisitor();

        e.accept(cv);

        MatrixCalculations.show(cv.getValue());
    }
}
