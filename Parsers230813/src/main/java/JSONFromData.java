import org.json.JSONArray;
import org.json.JSONObject;

public class JSONFromData {
    public static String createPerson() {
        JSONObject joDocument = new JSONObject();
        joDocument.put("number", 111222);
        joDocument.put("serial", 1098349597);

        JSONObject joPerson1 = new JSONObject();
        joPerson1.put("firstname", "Aleksei");
        joPerson1.put("lastname", "Kazantsev");
        joPerson1.put("age", 43);
        joPerson1.put("coder", true);
        joPerson1.put("document", joDocument);

        return joPerson1.toString();

        /*
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(joPerson1);
        jsonArray.put(joPerson1);

        System.out.println(jsonArray);
         */
    }
}
