package com.whfp.anti_terrorism.validation;

import com.mobsandgeeks.saripaar.AnnotationRule;

import java.lang.annotation.Annotation;
import java.text.ParseException;

/**
 * 自定义身份证验证  需配合android-saripaar:2.0.3 验证框架使用
 * Created by 张明杨 on 2018-04-28-0028.
 */

public class IdCardRule extends AnnotationRule<IdCard, String> {
    /**
     * Constructor. It is mandatory that all subclasses MUST have a constructor with the same
     * signature.
     *
     * @param idCard The rule {@link Annotation} instance to which
     *               this rule is paired.
     */
    protected IdCardRule(IdCard idCard) {
        super(idCard);
    }

    @Override
    public boolean isValid(String s) {
        boolean isValid = true;
//        boolean isValid = mRuleAnnotation.isIdCard();
        if (s.equals("") || s.length() <= 0) {
            isValid = false;
        }
        try {
            if (!IDCardUtils.IDCardValidate(s).equals("") || IDCardUtils.IDCardValidate(s).length() > 0) {
                isValid = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            isValid = false;
        }

        return isValid;
    }
}
