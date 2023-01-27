package DP;

public class Scalar implements Element{
    Element parent;
    String name;
    double val;

    boolean optional_name;
    boolean initialized;

    public Scalar(String _name, double _val) {
        name = _name;
        val = _val;
        optional_name = false;

        initialized = true;
    }

    public Scalar(String _name, Element _parent) {
        name = _name;
        parent = _parent;

        optional_name = true;
        initialized = false;
    }
    public Scalar(){
        optional_name = true;

        initialized = false;
    }

    @Override
    public void accept(Visitor v) throws Exception {
        v.visit(this);
    }
}
