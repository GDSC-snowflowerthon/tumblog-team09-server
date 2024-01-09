package snowflake.tumblog.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import snowflake.tumblog.common.enums.BaseResponseStatus;

@Getter
@Setter
@AllArgsConstructor
public class BaseException extends Exception {
    private BaseResponseStatus status;
}
