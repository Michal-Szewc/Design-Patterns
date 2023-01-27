package dp;

import java.util.Stack;

public class JSONObjectBuilderImp implements JSONObjectBuilder{

    Stack<JSONObject> objs = new Stack<>();
    JSONObject last = new JSONObject();

    @Override
    public void reset() {
        while(!this.objs.empty()) {
            this.objs.pop();
        }
        this.objs.push(new JSONObject());
    }

    @Override
    public void addInteger(String name, int x) {
        objs.peek().addProperty(name, new JSONInteger(x));
    }

    @Override
    public void addFloat(String name, float x) {
        objs.peek().addProperty(name, new JSONFloat(x));
    }

    @Override
    public void addString(String name, String s) {
        objs.peek().addProperty(name, new JSONString(s));
    }

    @Override
    public void addInnerJSONObject(String name) {
        JSONObject o = new JSONObject();
        objs.peek().addProperty(name, o);
        objs.push(o);
    }

    @Override
    public void closeInnerJSONObject() {
        last = objs.pop();
    }

    public JSONObject create(){
        return last;
    }
}
