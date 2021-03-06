package com.whfp.anti_terrorism.validation;

import com.mobsandgeeks.saripaar.annotation.ValidateUsing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义 手机验证注解类
 * 需配合android-saripaar:2.0.3 验证框架使用
 * Created by 张明杨 on 2018-04-28-0028.
 */
@ValidateUsing(MobileRule.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Mobile {

    int messageResId() default -1;                     // Mandatory attribute

    String message() default "请输入正确的手机号码";   // Mandatory attribute

    int sequence() default -1;                     // Mandatory attribute
}
