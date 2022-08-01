package com.spring.learn.converter;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Set;

/**
 * 传入到服务器端的header中文参数都会被 encoder，可以通过GenericConverter实现解码
 */
public class RequestHeaderDecodeConverter implements GenericConverter {
    private static final String DEFAULT_ENCODE = "utf-8";
    private String encoder = null;

    public RequestHeaderDecodeConverter(@Nullable String encoder) {
        this.encoder = encoder == null ? DEFAULT_ENCODE : encoder;
    }

    @Override
    public Set<GenericConverter.ConvertiblePair> getConvertibleTypes() {
        Set<ConvertiblePair> pairs = new HashSet<>();
        pairs.add(new ConvertiblePair(String.class, String.class));
        return pairs;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null) {
            return null;
        }

        Object userName = source;
        if (targetType.hasAnnotation(RequestHeader.class) && targetType.getType().equals(String.class)) {
            try {
                userName = URLDecoder.decode(source.toString(), encoder);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return userName;
    }
}
