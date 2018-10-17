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
public class MobileRule extends AnnotationRule<Mobile, String> {

    /**
     * Constructor. It is mandatory that all subclasses MUST have a constructor with the same
     * signature.
     *
     * @param mobile The rule {@link Annotation} instance to which
     *               this rule is paired.
     */
    protected MobileRule(Mobile mobile) {
        super(mobile);
    }

    @Override
    public boolean isValid(String s) {
        boolean isValid = true;
        isValid = isChinaPhoneLegal(s);
        return isValid;
    }

//        1、手机号开头集合
//        166，
//        176，177，178
//        180，181，182，183，184，185，186，187，188，189
//        145，147
//        130，131，132，133，134，135，136，137，138，139
//        150，151，152，153，155，156，157，158，159
//        198，199

    // 2、正则表达式
    public boolean isChinaPhoneLegal(String str)
            throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(166)|(17[0-8])|(18[0-9])|(19[8-9])|(147,145))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

}
