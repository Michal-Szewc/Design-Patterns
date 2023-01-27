package DP;

public class TextVisitor implements Visitor{
    StringBuilder equation = new StringBuilder();
    private boolean show_all = false;

    @Override
    public void visit(Add add) throws Exception{
        equation.append("(");
        add.a.accept(this);
        equation.append(" + ");
        add.b.accept(this);
        equation.append(")");
    }

    @Override
    public void visit(Subtract sub) throws Exception{
        equation.append("(");
        sub.a.accept(this);
        equation.append(" - ");
        sub.b.accept(this);
        equation.append(")");
    }

    @Override
    public void visit(Multiply mul) throws Exception{
        mul.a.accept(this);
        equation.append(" * ");
        mul.b.accept(this);
    }

    @Override
    public void visit(Scale s) throws Exception{
        s.a.accept(this);
        equation.append(" * ");
        s.s.accept(this);
    }

    @Override
    public void visit(Matrix m) throws Exception{
        if(show_all && !m.optional_name) {
            equation.append("[");
            equation.append(m.name);
            equation.append("]");
        }

        if(!m.initialized){
            m.parent.accept(this);
        }
    }

    @Override
    public void visit(Scalar s) throws Exception{
        if(show_all && !s.optional_name) {
            equation.append("[");
            equation.append(s.name);
            equation.append("]");
        }

        if(!s.initialized){
            s.parent.accept(this);
        }
    }

    @Override
    public void visit(Inversion inv) throws Exception{
        equation.append("inv(");
        inv.m.accept(this);
        equation.append(")");
    }

    @Override
    public void visit(Det det) throws Exception{
        equation.append("det(");
        det.m.accept(this);
        equation.append(")");
    }

    public String getText(){
        return equation.toString();
    }

    public void show_hidden_names(boolean show_hidden_names){
        show_all = !show_hidden_names;
    }
}
