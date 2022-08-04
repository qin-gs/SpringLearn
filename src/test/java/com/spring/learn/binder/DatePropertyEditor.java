package com.spring.learn.binder;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义 String -> Date 类型转换
 */
public class DatePropertyEditor extends PropertyEditorSupport {
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            super.setValue(new SimpleDateFormat(DATE_FORMAT).parse(text));
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getAsText() {
        Date date = (Date) super.getValue();
        return new SimpleDateFormat(DATE_FORMAT).format(date);
    }
}
