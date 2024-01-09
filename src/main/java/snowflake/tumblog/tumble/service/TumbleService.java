package snowflake.tumblog.tumble.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import snowflake.tumblog.common.BaseException;
import snowflake.tumblog.common.BaseResponseStatus;
import snowflake.tumblog.tumble.domain.Tumble;
import snowflake.tumblog.tumble.dto.PostTumbleReq;
import snowflake.tumblog.tumble.repository.TumbleRepository;
import snowflake.tumblog.user.domain.User;
import snowflake.tumblog.user.domain.repository.UserRepository;

import static snowflake.tumblog.common.Constant.ACTIVE;

@Service
@AllArgsConstructor
public class TumbleService {
    private final TumbleRepository tumbleRepository;
    private final UserRepository userRepository;

    /**
     * 텀블 등록
     * menu, discountPrice, size
     */
    public void addTumble(PostTumbleReq postTumbleReq, Long userId) throws BaseException {
        try {
            User user = userRepository.findByIdAndStatusEquals(userId, ACTIVE)
                    .orElseThrow(() -> new BaseException(BaseResponseStatus.INVALID_USER_ID));

            Tumble tumble = Tumble.builder()
                    .user(user)
                    .menu(postTumbleReq.getMenu())
                    .discountPrice(postTumbleReq.getDiscountPrice())
                    .size(postTumbleReq.getSize())
                    .build();
            tumbleRepository.save(tumble);
        } catch(BaseException e) {
            throw e;
        } catch(Exception e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}
