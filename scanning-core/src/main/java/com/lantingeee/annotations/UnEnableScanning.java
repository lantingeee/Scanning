package com.lantingeee.annotations;

import java.lang.annotation.*;

/**
 * Created by lantingeee on 09/12/2017.
 */


@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface UnEnableScanning {
    boolean value() default true;
}
