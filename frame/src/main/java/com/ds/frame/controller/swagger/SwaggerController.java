package com.ds.frame.controller.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author raptor
 * @description SwaggerController
 * @date 2021/7/6 19:59
 */
@ApiIgnore
@Controller
@RequestMapping("/swagger")
public class SwaggerController{

    @GetMapping()
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("/api/swagger-ui.html");
    }
}
