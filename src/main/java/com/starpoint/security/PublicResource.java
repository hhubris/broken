package com.starpoint.security;

import java.lang.annotation.*;

/**
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PublicResource {
}