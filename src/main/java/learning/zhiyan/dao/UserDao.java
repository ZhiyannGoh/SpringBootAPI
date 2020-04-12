package learning.zhiyan.dao;

import learning.zhiyan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface UserDao extends JpaRepository<User, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id = :t")
    int deleteUser(@Param("t") long id);

}
