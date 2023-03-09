public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        System.out.println("This is an example");

        //Skapa ett JSON Object
        JSONObject jsonOb = new JSONObject();

        //Spara värden i JSON Object

        jsonOb.put("namn","Marcus");
        jsonOb.put("age",34);

        //Skriva Ut värden
        System.out.println("Mitt namn är: "+ jsonOb.get("namn"));
        System.out.println("Jag är "+ jsonOb.get(" år gammal. "));
    }
}