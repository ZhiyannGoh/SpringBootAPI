package learning.zhiyan.dao;

import learning.zhiyan.entity.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHistoryDao extends JpaRepository<UserHistory, Long> {

}
