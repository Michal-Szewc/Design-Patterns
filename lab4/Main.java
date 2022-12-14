import dp.*;

public class Main {
    public static void main(String[] args) {
        String filePath = "./objekt1.json";

        JSONObject obj = new JSONFileParser().parse(filePath);

        obj.show();
    }
}