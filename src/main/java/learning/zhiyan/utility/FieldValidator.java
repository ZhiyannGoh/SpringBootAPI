package learning.zhiyan.utility;

import learning.zhiyan.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FieldValidator {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    public boolean checkStringNullOrEmpty(String field) {
        String checkedField = Optional.ofNullable(field).orElse("");
        if (checkedField.trim().isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean checkIntIfNullOrZero(int field) {
        int checkedField = Optional.ofNullable(field).orElse(0);
        return checkedField == 0 ? true : false;
    }

    public <T> T checkIfUpdatedIsNull(T current, T update) {
        if (update == null) {
            return current;
        }
        return update;
    }

    public <T> String populateFlag(T firstValue, T secondValue, String currentFlag) {

        String flag="";

        if (firstValue.equals(secondValue)) {
            flag="N";
        } else {
            flag="Y";
        }
        return currentFlag.concat(flag);
    }

}
