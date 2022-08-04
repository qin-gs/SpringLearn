package com.spring.learn.editor;

import java.beans.PropertyEditorSupport;
import java.util.Locale;

/**
 * 自定义一个类型转换器，通常在 Controller 中结合 @InitBinder 使用，通过 CustomEditorConfigurer 注册到 spring 中 (类级别的)
 * 也可以实现 WebBindInitializer 接口注册全局属性编辑器 (全局的)
 * <p>
 * InitBinderDataBinderFactory用 于处理标注有 @InitBinder 的方法做初始绑定
 * <p>
 * String -> ExoticType
 */
public class ExoticTypeEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(new ExoticType(text.toUpperCase(Locale.ROOT)));
    }
}
