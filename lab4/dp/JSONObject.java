package dp;

import java.util.HashMap;
import java.util.Map;

public class JSONObject extends JSONEntity{

    private HashMap<String, JSONEntity> values = new HashMap<>();

    JSONObject(){}

    JSONEntity get(String name){
        if(values.containsKey(name))
            return values.get(name);
        return null;
    }

    void addProperty(String name, JSONEntity object){
        if(this.get(name) == null)
            values.put(name, object);
    }

    public void show(){
        System.out.println(this);
    }

    public String toStringIndent(String previousIndent){
        String currentIndent = previousIndent + "   ";

        StringBuilder toPrint = new StringBuilder();

        toPrint.append("{\n");

        for(Map.Entry<String, JSONEntity> e: values.entrySet()){
            toPrint.append(currentIndent);
            toPrint.append(e.getKey());
            toPrint.append(": ");
            toPrint.append(e.getValue().toStringIndent(currentIndent));
            toPrint.append(",\n");
        }

        toPrint.append(previousIndent);
        toPrint.append("}");

        return toPrint.toString();
    }

    @Override
    public String toString() {
        return toStringIndent("");
    }
}
