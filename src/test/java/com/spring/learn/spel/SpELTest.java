package com.spring.learn.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SpELTest {
    public static void main(String[] args) {
        // evaluation();
        // languageReferenceTest();
        operatorsTest();
    }

    public static void evaluation() {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        {
            // 普通字符串
            Expression exp = parser.parseExpression("'hello world'");
            String value = (String) exp.getValue();
            System.out.println(value);
        }
        {
            // 方法调用(method invocation)
            Expression exp = parser.parseExpression("'hello '.concat('world')");
            String value = (String) exp.getValue();
            System.out.println(value);
        }
        {
            // 调用bean属性(calling a javaBean property)
            Expression exp = parser.parseExpression("'hello'.bytes");
            byte[] value = (byte[]) exp.getValue();
            System.out.println(Arrays.toString(value));
        }
        {
            // 点符号(dot notation)
            Expression exp = parser.parseExpression("'hello'.bytes.length");
            int value = (int) exp.getValue();
            System.out.println(value);
        }
        {
            // 字符串的构造函数(string's constructor)
            Expression exp = parser.parseExpression("new String('hello').toUpperCase()");
            // 使用泛型消除强制类型转换
            String value = exp.getValue(String.class);
            System.out.println(value);
        }
        {
            // 支持泛型(generics-aware)
            class Simple {
                public final List<Boolean> booleanList = new ArrayList<>();
            }
            Simple simple = new Simple();
            simple.booleanList.add(true);
            // setValue会把字符串自动转换成boolean
            parser.parseExpression("booleanList[0]").setValue(context, simple, "false");

            boolean b = simple.booleanList.get(0);
            System.out.println(b);
        }

        {
            class Demo {
                public List<String> list;
            }
            // 初始化时自动设置null， 集合容量自动增长
            SpelParserConfiguration config = new SpelParserConfiguration(true, true);
            ExpressionParser p = new SpelExpressionParser(config);
            // 3超过了list的长度，会调用String默认的无参构造函数放进去，如果没有放进去null
            // 链式操作的时候不会产生空指针
            Expression exp = p.parseExpression("list[3]");
            Demo demo = new Demo();
            Object value = exp.getValue(demo);
            System.out.println(value); // 这里是""，不是null

        }

    }

    @SuppressWarnings("all")
    public static void languageReferenceTest() {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        {
            // 字面量表达式 Literal Expressions
            // evals to "Hello World"
            String helloWorld = (String) parser.parseExpression("'Hello World'").getValue();
            double avogadrosNumber = (Double) parser.parseExpression("6.0221415E+23").getValue();
            // evals to 2147483647
            int maxValue = (Integer) parser.parseExpression("0x7FFFFFFF").getValue();
            boolean trueValue = (Boolean) parser.parseExpression("true").getValue();
            Object nullValue = parser.parseExpression("null").getValue();
            Object value = parser.parseExpression("'c'").getValue();
            System.out.println(value.getClass());
        }
        {
            // properties, arrays, lists, maps, indexers
            // EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
            // int value = (int) parser.parseExpression("birthdate.year + 1990").getValue(context);
            // System.out.println(value);

        }
        {
            // EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
            //
            // Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");
            // // Inventions Array
            //
            // // evaluates to "Induction motor"
            // String invention = parser.parseExpression("inventions[3]").getValue(
            // 		context, tesla, String.class);
            //
            // // Members List
            //
            // // evaluates to "Nikola Tesla"
            // String name = parser.parseExpression("members[0].name").getValue(
            // 		context, ieee, String.class);
            //
            // // List and Array navigation
            // evaluates to "Wireless communication"
            // String invention = parser.parseExpression("members[0].inventions[6]").getValue(
            // 		context, ieee, String.class);
        }
        {
            // List
            List value = (List) parser.parseExpression("{1, 2, 3, 4}").getValue(context);
            System.out.println(value);

            List value1 = (List) parser.parseExpression("{{'a', 'b'}, {'c', 'd'}}").getValue(context);
            System.out.println(value1);
        }
        {
            // Map
            Map value1 = (Map) parser.parseExpression("{'a': 'b', 'c': 'd'}").getValue(context);
            System.out.println(value1);
        }
        {
            // Array
            int[] value = (int[]) parser.parseExpression("new int[4]").getValue(context);
            int[] value1 = (int[]) parser.parseExpression("new int[]{1, 2, 3}").getValue(context);
            int[][] value2 = (int[][]) parser.parseExpression("new int[2][3]").getValue(context);
            System.out.println(Arrays.toString(value));
            System.out.println(Arrays.toString(value1));
            System.out.println(Arrays.toString(value2));
        }
        {
            // method
            String value = parser.parseExpression("'abc'.substring(1, 3)").getValue(String.class);
            // Boolean value1 = parser.parseExpression("isMember('not a member')").getValue(context, Boolean.class);
            System.out.println(value);
            // System.out.println(value1);
        }
    }

    public static void operatorsTest() {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        {
            // 关系运算符 relational operators
            // null 小于任何值
            Boolean value = parser.parseExpression("2 == 3").getValue(Boolean.class);
            System.out.println(value);

            Boolean value1 = parser.parseExpression("'a' < 'b'").getValue(Boolean.class);
            System.out.println(value1);

            // instanceof  +  正则表达式
            // 1 instance of T(int)  ->  false
            // 1 instance of T(Integer)  ->  true
            Boolean value2 = parser.parseExpression("'xyz' instanceof T(Integer)").getValue(Boolean.class);
            System.out.println(value2);

            Boolean value3 = parser.parseExpression("'5.0' matches '^\\d\\.\\d$'").getValue(Boolean.class);
            System.out.println(value3);
        }
        {
            // 符号 文本(避免在一些xml中有特殊意义) 全部大小写不敏感
            // lt <		gt > 	le <= 	gt >=
            // eq == 	ne !=	div / 	mod % 	 not !
        }
        {
            // 逻辑运算符
            // and && 	or || 	not !
            // StandardStandardEvaluationContext c = new StandardEvaluationContext();
            // c.setBeanResolver(new BeanFactoryResolver(new Society()));
            // TODO
            // Boolean value = parser.parseExpression("isMember('23') or isMember('a')").getValue(context, Boolean.class);
            // System.out.println(value);
        }
        {
            // 算术操作
            Integer value = parser.parseExpression("1 + 2").getValue(Integer.class);
            System.out.println(value);
        }
        {
            // 赋值运算符
        }
    }
}
