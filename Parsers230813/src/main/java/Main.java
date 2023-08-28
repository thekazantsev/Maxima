import com.google.gson.Gson;
import com.google.gson.JsonObject;

import com.google.gson.stream.JsonReader;
import com.jayway.jsonpath.JsonPath;
import model.Person;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

/**
 * Взять небольшой json и распарсить его 4 способами: Data binding, Tree model, Streaming API, JsonPath
 * популярные библиотеки для парсинга: GSON, Jackson, JSON-simple
 * *
 * * Data binding
 * При десериализации передаем парсеру JSON и класс который нужно получить
 * При сериализации - просто указать объект
 * (Jackson library examples)
 * Product prod = obj.readValue(new File("product.json"), Product.class);
 * obj.writeValue(new File("product.json"), Product.class);
 * + простота
 * - расход памяти
 * *
 * * Tree model
 * Представляет JSON в виде дерева объектов класса Node или JsonElement. Навигация по дереву за программистом.
 * (Jackson library examples)
 * ObjectMapper mapper = new ObjectMapper();
 * node = mapper.readTree(json);
 * String city = node.path("address").path("city").asText();
 * *Json pointer (похож на Tree model)
 * JsonPointer activePointer = Json.createPointer("/Actors/007/BirthDate");
 * + бастрее Data binding, относительно простой
 * - сложнее Data binding, проблемы с памятью остаются
 * *
 * * Streaming API
 * Самый низкоуровневый способ, ручной разбор токенов JSON
 * (Jackson library examples)
 * while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
 *     result.add(jsonParser.getText());
 * }
 * + производительность, минимальное потребление памяти
 * - сложность использования
 * *
 * * JsonPath
 * Язык запросов по дереву JSON. Похож на XPath.
 * String jsonpathCreatorLocationPath = "$['tool']['jsonpath']['creator']['location'][*]";
 * List<Map<String, Object>> samMendesMovies = context.read("$[?(@.director=='Sam Mendes')]");
 * + позволяет быстро получить информацию из JSON по сложным критериям
 * - не подходит когда нужна вся информация из JSON
 *
 */

public class Main {
    public static void main(String[] args) throws IOException {
        String jsonString = JSONFromData.createPerson();

        System.out.println("\n=====DataBinding=====");
        Person person = new Gson().fromJson(jsonString, Person.class);
        System.out.println("person = " + person);

        System.out.println("\nTree model (v1)");
        String personDocumentNumber = new Gson()
                                            .toJsonTree(person)
                                            .getAsJsonObject().get("document")
                                            .getAsJsonObject().get("number")
                                            .getAsString();
        System.out.println("personDocumentNumber = " + personDocumentNumber);

        System.out.println("\nTree model (v2)");
        JsonObject jsonObject = new Gson().fromJson(jsonString, JsonObject.class);
        int serial = jsonObject.getAsJsonObject("document")
                               .getAsJsonPrimitive("serial")
                               .getAsInt();
        System.out.println("serial = " + serial);

        System.out.println("\nStreaming API");
        JsonFactory jsonFactory = new JsonFactory();
        JsonParser jsonParser = jsonFactory.createParser(jsonString);
        JsonToken jsonToken = jsonParser.nextToken();

        while (jsonToken != null) {
//            if (jsonToken == JsonToken.VALUE_STRING || jsonToken == JsonToken.FIELD_NAME) {
//                System.out.println(jsonParser.getText());
//            }
            System.out.println(jsonParser.getText());
            jsonToken = jsonParser.nextToken();
        }

        System.out.println("\nStreaming API (v2)");
        String jsonString2 =
                "{\"name\":\"Mahesh Kumar\", \"age\":21,\"verified\":false,\"marks\": [100,90,85]}";
        JsonReader reader = new JsonReader(new StringReader(jsonString2));
        try {
            handleJsonObject(reader);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n\nJsonPath");
        String booksJson = "{\n" +
                "    \"book\": \n" +
                "    [\n" +
                "        {\n" +
                "            \"title\": \"Beginning JSON\",\n" +
                "            \"author\": \"Ben Smith\",\n" +
                "            \"price\": 49.99\n" +
                "        },\n" +
                "\n" +
                "        {\n" +
                "            \"title\": \"JSON at Work\",\n" +
                "            \"author\": \"Tom Marrs\",\n" +
                "            \"price\": 29.99\n" +
                "        },\n" +
                "\n" +
                "        {\n" +
                "            \"title\": \"Learn JSON in a DAY\",\n" +
                "            \"author\": \"Acodemy\",\n" +
                "            \"price\": 8.99\n" +
                "        },\n" +
                "\n" +
                "        {\n" +
                "            \"title\": \"JSON: Questions and Answers\",\n" +
                "            \"author\": \"George Duckett\",\n" +
                "            \"price\": 6.00\n" +
                "        }\n" +
                "    ],\n" +
                "\n" +
                "    \"price range\": \n" +
                "    {\n" +
                "        \"cheap\": 10.00,\n" +
                "        \"medium\": 20.00\n" +
                "    }\n" +
                "}";

        List<Map<String, Object>> expensiveBooks = JsonPath.parse(booksJson)
                .read("$['book'][?(@['price'] > $['price range']['medium'])]");
        System.out.println(expensiveBooks);

        List<Map<String, Object>> allAuthors = JsonPath.parse(booksJson)
                .read("$['book'][*]['author']");
        System.out.println(allAuthors);

        List<Map<String, Object>> gerogeDuckett = JsonPath.parse(booksJson)
                .read("$['book'][?(@.author == 'George Duckett')]");
        System.out.println(gerogeDuckett);

        List<Map<String, Object>> fullJson = JsonPath.parse(booksJson)
                .read("$[*]");
        System.out.println(fullJson);
    }

    private static void handleJsonObject(JsonReader reader) throws IOException {
        reader.beginObject();
        String fieldname = null;

        while (reader.hasNext()) {
            com.google.gson.stream.JsonToken token = reader.peek();

            if (token.equals(com.google.gson.stream.JsonToken.BEGIN_ARRAY)) {
                System.out.print("Marks [ ");
                handleJsonArray(reader);
                System.out.print("]");
            } else if (token.equals(com.google.gson.stream.JsonToken.END_OBJECT)) {
                reader.endObject();
                return;
            } else {
                if (token.equals(com.google.gson.stream.JsonToken.NAME)) {
                    //get the current token
                    fieldname = reader.nextName();
                }

                if ("name".equals(fieldname)) {
                    //move to next token
                    token = reader.peek();
                    System.out.println("Name: "+reader.nextString());
                }

                if("age".equals(fieldname)) {
                    //move to next token
                    token = reader.peek();
                    System.out.println("Age:" + reader.nextInt());
                }

                if("verified".equals(fieldname)) {
                    //move to next token
                    token = reader.peek();
                    System.out.println("Coder:" + reader.nextBoolean());
                }
            }
        }
    }

    private static void handleJsonArray(JsonReader reader) throws IOException {
        reader.beginArray();

        while (true) {
            com.google.gson.stream.JsonToken token = reader.peek();

            if (token.equals(com.google.gson.stream.JsonToken.END_ARRAY)) {
                reader.endArray();
                break;
            } else if (token.equals(com.google.gson.stream.JsonToken.BEGIN_OBJECT)) {
                handleJsonObject(reader);
            } else if (token.equals(com.google.gson.stream.JsonToken.END_OBJECT)) {
                reader.endObject();
            } else {
                System.out.print(reader.nextInt() + " ");
            }
        }
    }
}
