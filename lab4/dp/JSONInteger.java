package dp;

public class JSONInteger  extends JSONEntity{
    public int val;

    JSONInteger(int _val){
        this.val = _val;
    }

    public String toStringIndent (String indent) {
        return Integer.toString(val);
    }
}
