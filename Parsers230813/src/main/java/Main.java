/**
 * Взять небольшой json и распарсить его 4 способами: Data binding, Tree model, Streaming API, JsonPath
 * популярные библиотеки для парсинга: GSON, Jackson, JSON-simple
 * *
 * * Data binding
 * При десериализации передаем парсеру JSON и класс который нужно получить
 * При сериализации - просто указать объект
 * Product prod = obj.readValue(new File("product.json"), Product.class);
 * obj.writeValue(new File("product.json"), Product.class);
 * + простота
 * - расход памяти
 * *
 * * Tree model
 * Представляет JSON в виде дерева объектов класса Node или JsonElement. Навигация по дереву за программистом.
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
    public static void main(String[] args) {

        //Data binding
    }
}
