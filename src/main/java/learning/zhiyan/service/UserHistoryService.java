package learning.zhiyan.service;

import learning.zhiyan.dao.UserHistoryDao;
import learning.zhiyan.entity.User;
import learning.zhiyan.entity.UserHistory;
import learning.zhiyan.utility.FieldValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class UserHistoryService {

    private static Logger logger = LoggerFactory.getLogger(UserHistoryService.class);

    @Autowired
    FieldValidator fieldValidator;
    @Autowired
    UserHistoryDao userHistoryDao;

    public void insertUserHistory(User beforeUserInfo, User updatedUserInfo) {

        if (beforeUserInfo.equals(updatedUserInfo)) {
            logger.info("Update not required - User information is the same");
        } else {

            String fname = beforeUserInfo.getFirstName();
            String lname = beforeUserInfo.getLastName();
            int age = beforeUserInfo.getAge();
            String gender = beforeUserInfo.getGender();

            String changeIndicator = "";
            changeIndicator = fieldValidator.populateFlag(fname, updatedUserInfo.getFirstName(), changeIndicator);
            changeIndicator = fieldValidator.populateFlag(lname, updatedUserInfo.getLastName(), changeIndicator);
            changeIndicator = fieldValidator.populateFlag(age, updatedUserInfo.getAge(), changeIndicator);
            changeIndicator = fieldValidator.populateFlag(gender, updatedUserInfo.getGender(), changeIndicator);

            UserHistory userHistory = new UserHistory();
            userHistory.setId(beforeUserInfo.getId());
            userHistory.setFirstName(fname);
            userHistory.setLastName(lname);
            userHistory.setAge(age);
            userHistory.setGender(gender);
            userHistory.setFlagIndicator(changeIndicator);
            userHistory.setUpdateTimestamp(new Timestamp(System.currentTimeMillis()));

            userHistoryDao.save(userHistory);
        }

    }

    public List<UserHistory> getAllUserHistory() {
        return userHistoryDao.findAll();
    }
}