package learning.zhiyan.entity;

import learning.zhiyan.jpa.UserHistoryComposite;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@DynamicUpdate
@IdClass(UserHistoryComposite.class)
@Table(name="user_history")
public class UserHistory {

    @Id
    @NotNull
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private int age;
    @NotNull
    private String gender;
    @NotNull
    private String flagIndicator;
    @Id
    @NotNull
    private Timestamp updateTimestamp;

}

