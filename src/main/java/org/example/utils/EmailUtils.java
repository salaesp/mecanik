package org.example.utils;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class EmailUtils {

    public static final String GMAIL = "GMAIL";
    public static final String DEFAULT = "DEFAULT";
    private static Map<String, Function<String, String>> mailStrategies =
            Map.of(
                    GMAIL, combineFunction(List.of(removeDots(), removePlus(), String::toUpperCase)),
                    DEFAULT, combineFunction(List.of(removePlus(), String::toUpperCase))
            );

    private EmailUtils() {
    }

    public static String cleanEmail(String email) {
        return mailStrategies.get(getDomain(email)).apply(email);
    }

    private static Function<String, String> combineFunction(List<Function<String, String>> functions) {
        return functions.stream().reduce(Function.identity(), Function::andThen);
    }

    private static String getDomain(String email) {
        int atIndex = email.indexOf("@");
        String fromAt = email.substring(atIndex);
        String domain = fromAt.substring(1, fromAt.indexOf("."));
        return domain.toUpperCase();
    }

    private static Function<String, String> removePlus() {
        return clearEmail -> {
            if (!clearEmail.contains("+")) return clearEmail;
            int atIndex = clearEmail.indexOf("@");
            int plusIndex = clearEmail.indexOf("+");
            return clearEmail.substring(0, plusIndex) + clearEmail.substring(atIndex);
        };
    }

    private static Function<String, String> removeDots() {
        return clearEmail -> {
            int atIndex = clearEmail.indexOf("@");
            String noDotsName = clearEmail.substring(0, atIndex).replace(".", "");
            return noDotsName + clearEmail.substring(atIndex);
        };
    }
}
