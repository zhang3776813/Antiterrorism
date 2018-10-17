package com.whfp.anti_terrorism.validation;

import com.mobsandgeeks.saripaar.AnnotationRule;

import java.lang.annotation.Annotation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 自定义手机号验证  需配合android-saripaar:2.0.3 验证框架使用
 * Created by 张明杨 on 2018-04-28-0028.
 */
public class UserNameRule extends AnnotationRule<UserName, String> {


    /**
     * Constructor. It is mandatory that all subclasses MUST have a constructor with the same
     * signature.
     *
     * @param userName The rule {@link Annotation} instance to which
     *                 this rule is paired.
     */
    protected UserNameRule(UserName userName) {
        super(userName);
    }

    @Override
    public boolean isValid(String s) {
        boolean isValid = true;
        isValid = isChinaPhoneLegal(s);
        return isValid;
    }

    /**
     * 验证用户名
     * 只能是字母、数字、下划线、大于6位，小于16位
     *
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^(\\w){6,16}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

}
