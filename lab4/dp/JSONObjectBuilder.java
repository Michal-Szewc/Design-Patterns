package dp;

public interface JSONObjectBuilder {

    void reset();

    void addInteger(String name, int x);

    void addFloat(String name, float x);

    void addString(String name, String s);

    void addInnerJSONObject(String name);

    void closeInnerJSONObject();

    JSONObject create();

}
