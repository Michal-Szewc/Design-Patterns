package dp;

public class JSONFloat extends JSONEntity{
    public float val;

    JSONFloat(float _val){
        this.val = _val;
    }

    public String toStringIndent (String indent) {
        return Float.toString(val);
    }
}
