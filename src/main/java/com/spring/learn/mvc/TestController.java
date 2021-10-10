package com.spring.learn.mvc;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

/**
 * 方法允许配置的<a href="https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-arguments">参数</a>
 */
@RequestMapping("test")
@Controller
public class TestController {

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
}
