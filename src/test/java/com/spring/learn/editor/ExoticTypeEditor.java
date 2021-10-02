package com.spring.learn.editor;

import java.beans.PropertyEditorSupport;
import java.util.Locale;

/**
 * 自定义一个类型转换器，通常在Controller中结合@InitBinder使用
 * <p>
 * String -> ExoticType
 */
public class ExoticTypeEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(new ExoticType(text.toUpperCase(Locale.ROOT)));
    }
}
