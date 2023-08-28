import model.Document;
import model.Person;
import org.json.JSONObject;

public class JSONFromObject {
    public static String createPerson() {
        Document document = new Document();
        document.setNumber(333444);
        document.setSerial(223344556);

        Person person = new Person();
        person.setFirstname("Aleksei");
        person.setLastname("Ka");
        person.setAge(38);
        person.setCoder(true);
        person.setDocument(document);

        JSONObject joPerson2 = new JSONObject(person);

        return joPerson2.toString();
    }
}
