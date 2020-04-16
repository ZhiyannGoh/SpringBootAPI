package learning.zhiyan.service;

import learning.zhiyan.dao.UserDao;
import learning.zhiyan.entity.User;
import learning.zhiyan.utility.FieldValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static learning.zhiyan.constant.Constant.*;

@Service
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private FieldValidator fieldValidator;
    @Autowired
    private UserHistoryService userHistoryService;

    public List<User> getAllUser() {
        return userDao.findAll();
    }

    public boolean createUser(String fname, String lname, int age, String gender) {

        if (fieldValidator.checkStringNullOrEmpty(fname)
                || fieldValidator.checkStringNullOrEmpty(lname)
                || fieldValidator.checkStringNullOrEmpty(gender)
                || fieldValidator.checkIntIfNullOrZero(age)) {
            logger.error("user field should not be empty");
            return false;
        }

        String dateStamp = DATE_FORMAT.format(new Date());

        User newUser = new User();
        newUser.setFirstName(fname);
        newUser.setLastName(lname);
        newUser.setAge(age);
        newUser.setGender(gender);
        newUser.setInsertDate(dateStamp);
        User persistUser = userDao.save(newUser);

        logger.info("Created User: " + persistUser.toString());


        return true;
    }

    public boolean deleteUser(long id) {

        if(userDao.findById(id).isPresent()) {

            User toDelete = userDao.findById(id).get();
            int deletedRecord = userDao.deleteUser(toDelete.getId());
            logger.info("Number of records deleted: " + deletedRecord);

            if (deletedRecord == 1) {
                userHistoryService.insertDeletedUser(toDelete);
                return true;
            }
        }

        logger.error("Deleted record should equals to 1");
        return false;
    }

    public boolean updateUser(Map<String, String> updateUserInfo) {

        if (updateUserInfo.get("u.id") == null) {
            logger.error("User ID cannot be null for update request");
            return false;
        }

        Long id = Long.parseLong(updateUserInfo.get("u.id"));
        if (userDao.findById(id).isPresent()) {

            logger.info("Before update: " + userDao.findById(id).get());

            User currentUserSnap = userDao.findById(id).get();
            String fname = fieldValidator.checkIfUpdatedIsNull(currentUserSnap.getFirstName(), updateUserInfo.get("u.fname"));
            String lname = fieldValidator.checkIfUpdatedIsNull(currentUserSnap.getLastName(), updateUserInfo.get("u.lname"));
            int age = fieldValidator.checkIfUpdatedIsNull(currentUserSnap.getAge(), Integer.parseInt(updateUserInfo.get("u.age")));
            String gender = fieldValidator.checkIfUpdatedIsNull(currentUserSnap.getGender(), updateUserInfo.get("u.gender"));

            User updatedUserInfo = User.builder()
                    .id(currentUserSnap.getId())
                    .firstName(fname)
                    .lastName(lname)
                    .age(age)
                    .gender(gender)
                    .insertDate(currentUserSnap.getInsertDate())
                    .build();

            logger.info("Current User Snapshot: " +currentUserSnap.toString());
            logger.info("Updated User: " +updatedUserInfo.toString());
            userHistoryService.insertUserHistory(currentUserSnap,updatedUserInfo);
            userDao.save(updatedUserInfo);
            logger.info("After update: " + userDao.findById(id).get());
            return true;
        }

        logger.info("User ID not found");
        return false;
    }
}
