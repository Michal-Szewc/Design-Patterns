package dp;

public interface JSONObjectBuilder {

    void reset();

    void addInteger(String name, int x);

    void addFloat(String name, float x);

    void addString(String name, String s);

    void addJSONObject(String name, JSONObject o);

    JSONObject create();

}
