package com.spring.learn.valid.group;

import com.spring.learn.valid.PersonForm;
import com.spring.learn.valid.self.Forbidden;
import org.hibernate.validator.constraints.Length;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.groups.ConvertGroup;
import java.io.Serializable;

/**
 * 分组校验，级联校验，配置验证顺序
 */
@GroupSequence({Second.class, First.class})
public class User implements Serializable {

    @NotNull(message = "用户 id 不能为空", groups = {First.class})
    private Long id;

    @Length(min = 5, max = 20, message = "{user.name.length}", groups = {First.class, Second.class})
    @Pattern(regexp = "\\w{5,20}", groups = {Second.class})
    private String name;

    /**
     * 自定义校验器
     */
    @NotNull
    @Forbidden
    private String password;

    /**
     * 级联验证 (可能需要转换分组，使用 ConvertGroup 完成)
     */
    @Valid
    @ConvertGroup(from = First.class, to = Second.class)
    private PersonForm form;
}
