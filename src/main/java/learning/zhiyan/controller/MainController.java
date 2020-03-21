package learning.zhiyan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private static Logger logger = LoggerFactory.getLogger(MainController.class);

    @RequestMapping("/")
    public String welcomeMessage() {
        return "Congratulations - Spring Boot Web is running!";
    }

}