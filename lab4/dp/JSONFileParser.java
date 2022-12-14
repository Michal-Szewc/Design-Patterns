package dp;

import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONFileParser {

    public JSONFileParser(){}

    public JSONObject parse(String filename){
        String fileContent = "";

        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filename));
            fileContent = new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JSONStringParser().parse(fileContent);
    }
}
