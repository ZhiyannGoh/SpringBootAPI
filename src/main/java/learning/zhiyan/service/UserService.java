package learning.zhiyan.service;

import learning.zhiyan.constant.Constant;
import learning.zhiyan.dao.UserDao;
import learning.zhiyan.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    public List<User> getAllUser() {
        return userDao.findAll();
    }

    public boolean createUser(String fname, String lname, int age, String gender) {
        String validateFname = Optional.ofNullable(fname).orElse("");
        String validateLname = Optional.ofNullable(lname).orElse("");
        int validateAge = Optional.ofNullable(age).orElse(0);
        String validateGender = Optional.ofNullable(gender).orElse("");

        if(validateFname.trim().isEmpty()
                || validateLname.trim().isEmpty()
                || validateGender.trim().isEmpty()
                || validateAge == 0) {
            return false;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.DATESTAMP_PATTERN);
        String dateStamp = dateFormat.format(new Date());

        User newUser = new User();
        newUser.setFirstName(validateFname);
        newUser.setLastName(validateLname);
        newUser.setAge(validateAge);
        newUser.setGender(validateGender);
        newUser.setInsertDate(dateStamp);

        userDao.save(newUser);
        return true;
    }
}
