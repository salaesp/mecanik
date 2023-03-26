package org.example.utils;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class EmailUtils {

    public static final String GMAIL = "GMAIL";
    public static final String DEFAULT = "DEFAULT";
    private static Map<String, Function<String, String>> mailStrategies =
            Map.of(
                    GMAIL, combineFunction(List.of(removeDots(), removePlus(), String::toLowerCase)),
                    DEFAULT, combineFunction(List.of(removePlus(), String::toLowerCase))
            );

    private EmailUtils() {
    }

    public static String cleanEmail(String email) {
        Function<String, String> strategy = Optional.ofNullable(mailStrategies.get(getDomain(email)))
                .orElse(mailStrategies.get(DEFAULT));
        return strategy.apply(email);
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
