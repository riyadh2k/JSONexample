import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {

        System.out.println("Hello world!");
        System.out.println("This is an example");

        //Skapa ett JSON Object
        JSONObject jsonOb = new JSONObject();

        //Spara värden i JSON Object

        jsonOb.put("namn","Marcus");
        jsonOb.put("age",34);

        //Skriva Ut värden
        System.out.println("Mitt namn är: "+ jsonOb.get("namn"));
        System.out.println("Jag är "+ jsonOb.get("age"));

        Object o = new JSONParser().parse(new FileReader("/Users/shabenur/Desktop/Java Web Service/JSON example/src/data.json"));
        JSONObject jsonData = (JSONObject) o;
        JSONObject person = (JSONObject) jsonData.get("p1");
        String name= (String) person.get("name");
        long age= (long) person.get("age");
        String favColor= (String) person.get("favoriteColor");
        System.out.println("P1 Name :" + name);
        System.out.println("P1 age :" + age);
        System.out.println("P1 favColor :" + favColor);

        //fetchJsonFromFile();
        fetchJsonFromAPI();

    }
    static void fetchJsonFromFile() throws IOException, ParseException {
        String filepath = "/Users/shabenur/Desktop/Java Web Service/JSON example/src/data.json";

        // Hämta data från JSON fil

        JSONObject fetchData= (JSONObject) new JSONParser().parse(new FileReader(filepath));

        // Konvertera da till ett JSONObject
        JSONObject p1= (JSONObject) fetchData.get("p1");
        JSONObject p2= (JSONObject) fetchData.get("p2");

        //Hämta och skriv ut data
        String nameP1=p1.get("name").toString(),  nameP2=p2.get("name").toString();
        int agep1=Integer.parseInt(p1.get("age").toString()), agep2=Integer.parseInt(p2.get("age").toString());

        System.out.println("Mitt namn  är "+ nameP1+ " och jag är "+agep1+"år gammal");
        System.out.println("Mitt namn  är "+ nameP2+ " och jag är "+agep2+"år gammal");

    }

    static void fetchJsonFromAPI() throws IOException, ParseException {
        // skapa url till API
        URL url = new URL("https://api.wheretheiss.at/v1/satellites/25544");

        HttpURLConnection conn= (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.connect();
        if (conn.getResponseCode()== 200) {
            System.out.println("Koppling Lyckdas");

            StringBuilder strData = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                strData.append(scanner.nextLine());

            }
            scanner.close();
            JSONObject dataObject = (JSONObject) new JSONParser().parse(String.valueOf(strData));
            String visibility= (String) dataObject.get("visibility");
            String units= (String) dataObject.get("units");
            System.out.println(visibility);
            System.out.println(units);
        }
        else System.out.println("Koppling misslyckdas");

    }
}