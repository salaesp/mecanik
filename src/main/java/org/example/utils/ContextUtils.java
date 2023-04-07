package org.example.utils;

import lombok.experimental.UtilityClass;
import org.example.security.token.CustomUserAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class ContextUtils {

    public static String getUserEmail() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static Long getUserId() {
        return ((CustomUserAuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getUserId();
    }
}
