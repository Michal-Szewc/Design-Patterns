package dp;

public class JSONString extends JSONEntity{
    public String val;

    JSONString(String _val){
        this.val = _val;
    }

    public String toStringIndent (String indent) {
        return '\"' + val + '\"';
    }
}
