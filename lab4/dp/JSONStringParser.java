package dp;

public class JSONStringParser {

    JSONObjectBuilder builder;
    private int index;
    private int objs_in = 0;
    private boolean new_obj = false;

    public JSONStringParser(){
        builder = new JSONObjectBuilderImp();
        index = 0;
    }

    private JSONStringParser(int i){
        builder = new JSONObjectBuilderImp();
        index = i;
    }


    void next_nonwhite_char(char[] s, int i){
        while(i < s.length && (s[i] == ' ' || s[i] == '\n' || s[i] == '\r' || s[i] == '\t')){
            i++;
        }
        index =  i;
    }

    String get_string_from(char[] s, int i){
        return get_string_from(s, i, false);
    }
    String get_string_from(char[] s, int i, boolean trueString){
        StringBuilder out = new StringBuilder();

        if(trueString){
            while(i < s.length && s[i] != '\"'){
                out.append(s[i]);
                i++;
            }
            i++;
        }
        else {
            while (i < s.length && (Character.isAlphabetic(s[i]) || Character.isDigit(s[i]) || s[i] == '_')) {
                out.append(s[i]);
                i++;
            }
        }

        index = i;
        return out.toString();
    }

    public JSONObject parse(String string){
        return parse(string.toCharArray());
    }

    public JSONObject parse(char[] s){
        builder.reset();

        next_nonwhite_char(s, index);

        if(s[index] != '{')
            throw new IllegalArgumentException("The object must start with '{'");

        objs_in = 1;

        next_nonwhite_char(s, index + 1);




        String name;
        while(index < s.length && objs_in > 0){
            if(s[index] == '}'){
                objs_in -= 1;
                builder.closeInnerJSONObject();

                next_nonwhite_char(s, index + 1);

                if (objs_in > 0){
                    if(index >= s.length || (s[index] != ','))
                        throw new IllegalArgumentException("After each value there should be a ','");


                    next_nonwhite_char(s, index + 1);
                }

                continue;
            }
            name = get_string_from(s, index);
            next_nonwhite_char(s, index);
            if (index >= s.length || name.length() == 0)
                throw new IllegalArgumentException("The object must end with '}'");
            else if(s[index] != ':') {
                throw new IllegalArgumentException("After name there should be ':'");
            }
            next_nonwhite_char(s, index + 1);
            if(index >= s.length)
                throw new IllegalArgumentException("After ':' there  should be values");
            if(s[index] == '{'){
                objs_in += 1;
                builder.addInnerJSONObject(name);
                new_obj = true;
            }
            else if(s[index] == '\"'){
                builder.addString(name, get_string_from(s,index + 1, true));
            }
            else if(Character.isDigit(s[index])){
                String first = get_string_from(s, index);
                if(index >= s.length)
                    throw new IllegalArgumentException("After number there  should be ','");
                if(s[index] == '.'){
                    String second = get_string_from(s, index + 1);
                    builder.addFloat(name, Float.parseFloat(first + '.' + second));
                }
                else{
                    builder.addInteger(name, Integer.parseInt(first));
                }

            }
            else
                throw new IllegalArgumentException("invalid value. The parser only accepts  \"string\", {object}, int or float numbers");

            next_nonwhite_char(s, index);
            if(index >= s.length || (s[index] != ',') && (!new_obj))
                throw new IllegalArgumentException("After each value there should be a ','");

            next_nonwhite_char(s, index + 1);
            new_obj = false;
        }
        index++;

        return builder.create();
    }
}
