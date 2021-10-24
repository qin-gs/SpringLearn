package com.spring.learn.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 方法允许配置的<a href="https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-arguments">参数</a>
 * <p>
 * SessionAttribute 类级别的注解
 * 若希望在多个请求之间共用数据，Spring MVC会将存放在model中对应的数据暂存到HttpSession中，也可用于访问过滤器拦截器创建的预先存在的请求属性
 * names: 会将model中属性名为user的属性添加到会话中
 * type: 会将model中所有类型为Integer的属性添加到会话中
 * 如果要删除使用HttpServletRequest
 */
@RequestMapping("test")
@SessionAttributes(names = {"user"}, types = {Integer.class})
@Controller
public class TestController {

    /**
     * 可以用来生成session中的数据
     */
    @Autowired
    private HttpServletRequest request;

    /**
     * 矩阵变量(需要手动开启, xml或代码配置)
     * GET /owners/42;q=11;r=12/pets/21;q=22;s=23
     */
    @GetMapping("/owners/{ownerId}/pets/{petId}")
    public void findPet(@PathVariable String ownerId,
                        @PathVariable String petId,
                        @MatrixVariable MultiValueMap<String, String> matrixVars,
                        @MatrixVariable(pathVar = "petId", defaultValue = "0")
                                MultiValueMap<String, String> petMatrixVars) {

        // matrixVars: ["q" : [11,22], "r" : 12, "s" : 23]
        // petMatrixVars: ["q" : 22, "s" : 23]
    }

    /**
     * 限制 accept
     */
    @GetMapping(path = "/pets/{petId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object getPet(@PathVariable String petId) {
        // ...
        return new Object();
    }

    /**
     * 限制 content-type(还可以否定'!')
     */
    @PostMapping(path = "/pets", consumes = "!application/json")
    public void addPet(@RequestBody Object pet) {
        // ...
    }

    /**
     * 限制参数
     * params='myParam' 限制参数存在
     * params='!myParam' 限制参数不存在
     * params='myParam=myValue' 限制参数为指定值
     */
    @GetMapping(path = "/pets/{petId}", params = "myParam=myValue")
    public void findPet1(@PathVariable String petId) {
        // ...
    }

    @GetMapping(path = "/pets", headers = "myHeader=myValue")
    public void findPet2(@PathVariable String petId) {
        // ...
    }

    /**
     * ResponseBody可以直接返回Json结果
     * ResponseEntity 不仅可以返回json结果，还可以定义返回的HttpHeaders和HttpStatus
     */
    @RequestMapping("/responseEntity")
    public ResponseEntity<String> responseEntity(HttpEntity<byte[]> entity) {
        String headerName = entity.getHeaders().getFirst("headerName");
        byte[] body = entity.getBody();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("responseHeaders", "header value");
        // 写入响应流 添加到响应 设置响应状态码
        return new ResponseEntity<>("hello world", responseHeaders, HttpStatus.CREATED);
    }

    /**
     * 不处理请求
     * 注解在方法上，会在每一个@RequestMapping标注的方法前执行，如果有返回值，则自动将该返回值加入到ModelMap中
     * 默认的名字是object，可以自定义 @ModelAttribute(value = "name")
     * <p>
     * 如果被注解在@RequestMapping方法上，返回值被解释为模型属性而不是视图名称，类似于返回void
     */
    @ModelAttribute
    public User modelAttribute() {
        return new User();
    }

    @ModelAttribute
    public User modelAttribute(Model model) {
        // 隐式添加属性
        model.addAttribute(new Object());
        return new User();
    }

    /**
     * 如果注册了Converter<String, User>就可以将string转换成User
     * <p>
     * 数据绑定
     * 注解在参数上，表示从模型中获取参数，如果不存在需要首先实例化该参数添加到模型中
     * 数据绑定的结果可能是错误的，BindingResult参数检查是否有错误
     */
    @GetMapping("/mapping/{user}")
    public Object modelAttribute(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "errorPage";
        }
        return new Object();
    }

    /**
     * 获取之前存入session的属性
     */
    @GetMapping("/sessionAttribute")
    public Object sessionAttribute(@SessionAttribute("user") User user) {
        // 删除session中的数据
        request.getSession().removeAttribute("user");
        return new Object();
    }

    /**
     * 如果类型不匹配，会自动进行类型转换，或者使用 WebbDataBinder(@InitBinder注解) 自定义转换方式
     * 如果用Map会将所有的数据全部放进去
     */
    @RequestMapping("getValue")
    public Object getValue(@CookieValue("JSESSIONID") String sessionId,
                           @RequestHeader("Accept-Encoding") String encoding) {
        return sessionId;
    }

    /**
     * 同时上传文件和json对象，
     * json字符串一定要声明类型 Content-Type: application/json，否则使用@RequestPart注解无法反序列化
     */
    @PostMapping("fileUpload")
    public void fileUpload(@RequestPart("file") MultipartFile file,
                           @RequestPart("user") User user) {

    }

    /**
     * 错误页码配置
     * 可以获取HttpServletResponse中的状态码和错误信息
     */
    @RequestMapping(path = "/error", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map<String, Object> handle(HttpServletRequest request) {

        Map<String, Object> map = new HashMap<>();
        map.put("status", request.getAttribute("javax.servlet.error.status_code"));
        map.put("reason", request.getAttribute("javax.servlet.error.message"));
        return map;
    }

}
