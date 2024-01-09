package snowflake.tumblog.tumble.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import snowflake.tumblog.common.BaseException;
import snowflake.tumblog.common.constants.Constant;
import snowflake.tumblog.common.enums.BaseResponseStatus;
import snowflake.tumblog.tumble.domain.Tumble;
import snowflake.tumblog.tumble.dto.GetTumbleResponse;
import snowflake.tumblog.tumble.dto.PostTumbleRequest;
import snowflake.tumblog.tumble.repository.TumbleRepository;
import snowflake.tumblog.user.domain.User;
import snowflake.tumblog.user.domain.repository.UserRepository;

import static snowflake.tumblog.common.constants.Constant.ACTIVE;
import static snowflake.tumblog.common.enums.BaseResponseStatus.DATABASE_ERROR;
import static snowflake.tumblog.common.enums.BaseResponseStatus.INVALID_TUMBLE_ID;


@Service
@AllArgsConstructor
public class TumbleService {
    private final TumbleRepository tumbleRepository;
    private final UserRepository userRepository;

    /**
     * 텀블 등록
     * menu, discountPrice, size
     */
    public void addTumble(PostTumbleRequest postTumbleRequest, Long userId) throws BaseException {
        try {
            User user = userRepository.findByIdAndStatusEquals(userId, ACTIVE)
                    .orElseThrow(() -> new BaseException(BaseResponseStatus.INVALID_USER_ID));

            Tumble tumble = Tumble.builder()
                    .user(user)
                    .menu(postTumbleRequest.getMenu())
                    .discountPrice(postTumbleRequest.getDiscountPrice())
                    .size(postTumbleRequest.getSize())
                    .build();
            tumbleRepository.save(tumble);
        } catch(BaseException e) {
            throw e;
        } catch(Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    /**
     * 텀블 상세 조회
     */
    public GetTumbleResponse getTumble(Long tumbleId) throws BaseException {
        try {
            Tumble tumble = tumbleRepository.findByIdAndStatusEquals(tumbleId, ACTIVE).orElseThrow(() -> new BaseException(INVALID_TUMBLE_ID));

            // menu, discountPrice, size
            return new GetTumbleResponse(tumble.getMenu(), tumble.getDiscountPrice(),tumble.getSize());
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
