package dp;

public class JSONObjectBuilderImp implements JSONObjectBuilder{

    JSONObject obj;

    @Override
    public void reset() {
        this.obj = new JSONObject();
    }

    @Override
    public void addInteger(String name, int x) {
        obj.addProperty(name, new JSONInteger(x));
    }

    @Override
    public void addFloat(String name, float x) {
        obj.addProperty(name, new JSONFloat(x));
    }

    @Override
    public void addString(String name, String s) {
        obj.addProperty(name, new JSONString(s));
    }

    @Override
    public void addJSONObject(String name, JSONObject o) {
        obj.addProperty(name, o);
    }

    public JSONObject create(){
        return obj;
    }
}
