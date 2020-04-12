package learning.zhiyan.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserHistoryComposite implements Serializable {
    private long id;
    private Timestamp updateTimestamp;
}
