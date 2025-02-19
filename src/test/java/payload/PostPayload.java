package payload;

import java.util.stream.Stream;

public class PostPayload {

    public static String getPostPayload(String userId, String title, String body, String id) {
//        return "{\n" +
//                "    \"userId\": " + userId + ",\n" +
//                "    \"title\": \"" + title + "\",\n" +
//                "    \"body\": \"" + body + "\"\n" +
//                "}";
        return postPayload(userId, title, body, null);
    }

    public static String putPostPayload(String userId, String title, String body, String id) {
        return postPayload(userId, title, body, id);
    }

    private static String postPayload(String userId, String title, String body, String id) {
        return "{\n" +
                "    \"userId\": " + userId + ",\n" +
                "    \"title\": \"" + title + "\",\n" +
                "    \"body\": \"" + body + "\"" +
                (id != null ? ",\n    \"id\": \"" + id + "\"\n" : "\n") +
                "}";
    }
}
