package learning.zhiyan.controller;

import learning.zhiyan.entity.User;
import learning.zhiyan.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping("/user")
    @ResponseBody
    private List<User> getUserHTTP() {
        return userService.getAllUser();
    }

    @PostMapping("/createUser")
    @ResponseBody
    private HttpStatus createUserHTTP(@RequestParam("u.fname") String fname, @RequestParam("u.lname") String lname,
                                  @RequestParam("u.age") int age, @RequestParam("u.gender") String gender) {

        return userService.createUser(fname,lname,age,gender) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

    }

}


