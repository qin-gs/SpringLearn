package com.spring.learn.converter;

import com.spring.learn.bean.User;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * 假设有一项需求是希望能通过 user 的 id 或者 username 直接转换为对应的 user 对象
 */
public class UserGenericConverter implements GenericConverter {

    @Resource
    private UserService userService;

    /**
     * 把 source 对象从 sourceType 转换成 targetType
     */
    @Override
    public Object convert(Object source, TypeDescriptor sourceType,
                          TypeDescriptor targetType) {
        if (source == null) {
            return null;
        }
        User user = null;
        if (sourceType.getType() == Integer.class) {
            user = userService.findById((Integer) source);// 根据id来查找user
        } else if (sourceType.getType() == String.class) {
            user = userService.findByName((String) source);// 根据用户名来查找user
        }
        return user;
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        Set<ConvertiblePair> pairs = new HashSet<>();
        pairs.add(new ConvertiblePair(Integer.class, User.class));
        pairs.add(new ConvertiblePair(String.class, User.class));
        return pairs;
    }
}


class UserService {

    public User findById(Integer id) {
        return null;
    }

    public User findByName(String name) {
        return null;
    }
}
