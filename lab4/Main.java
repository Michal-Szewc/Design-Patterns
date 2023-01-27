import dp.*;

//
// format JSON:
// każda nazwa pola obiektu składa się z liter, cyfr i specjalnych znaków _ oraz /
// każda linijka poza otwarciem obiektu i samym końcem kończy się przecinkiem
//
// Przykładowa moja struktura JSONa
// {
// name: "Jan",
// age: 34,
// body: {
//     height: 169,
//     weight: 123 ,
//     hair: {
//         colour: "blond",
//         length: 37,
//         },
//     },
// car: {
//     topSpeed: 200,
//     colour: "red",
//     fast: "yes",
//     },
// }
//


public class Main {
    public static void main(String[] args) {
        String filePath = "./objekt1.json";
        JSONObject obj = new JSONObject();
        try{
            obj = new JSONFileParser().parse(filePath);
        } catch (Exception e) {
            System.out.println("The objekt1.json file didn't contain JSON that is required by my program");
            System.out.println("\nIn case of mistake in JSON file, the obj will be empty JSON object");
            e.printStackTrace();
        } finally {
            obj.show();
        }
    }
}