package DP;

public class Matrix implements Element{
    Element parent;
    String name;

    int size_x;
    int size_y;

    boolean optional_name;
    boolean initialized;

    double[][] tab;


    public Matrix(String _name, double[][] _tab){
        name = _name;
        size_x = _tab.length;
        size_y = _tab[0].length;
        optional_name = false;
        tab = _tab;

        initialized = true;
    }

    public Matrix(String _name, int _x, int _y, Element _parent){
        name = _name;
        size_x = _x;
        size_y = _y;
        parent = _parent;
        optional_name = true;

        initialized = false;
    }

    @Override
    public void accept(Visitor v) throws Exception {
        v.visit(this);
    }
}
