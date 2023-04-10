package org.example.security.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.example.security.config.SecurityConfig.HAS_CAR_OWNER_ROLE;
import static org.example.security.config.SecurityConfig.HAS_SHOP_OWNER_ROLE;

@PreAuthorize(HAS_CAR_OWNER_ROLE)
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface AccessedOnlyByCarOwner {

}
