package snowflake.tumblog.common;

import jakarta.persistence.Column;
import java.time.LocalDateTime;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

public class BaseEntity {

    @Column(columnDefinition = "varchar(10) default 'active'")
    @Setter
    private String status;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}