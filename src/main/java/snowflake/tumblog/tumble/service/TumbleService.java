package snowflake.tumblog.tumble.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snowflake.tumblog.common.BaseException;
import snowflake.tumblog.common.enums.BaseResponseStatus;
import snowflake.tumblog.tumble.domain.Size;
import snowflake.tumblog.tumble.domain.Tumble;
import snowflake.tumblog.tumble.dto.TumbleDetailResponse;
import snowflake.tumblog.tumble.dto.CreateTumbleRequest;
import snowflake.tumblog.tumble.repository.TumbleRepository;
import snowflake.tumblog.user.domain.User;
import snowflake.tumblog.user.domain.repository.UserRepository;

import static snowflake.tumblog.common.constants.Constant.ACTIVE;
import static snowflake.tumblog.common.enums.BaseResponseStatus.DATABASE_ERROR;
import static snowflake.tumblog.common.enums.BaseResponseStatus.INVALID_TUMBLE_ID;


@Transactional
@RequiredArgsConstructor
@Service
public class TumbleService {

    private final TumbleRepository tumbleRepository;
    private final UserRepository userRepository;

    public void create(CreateTumbleRequest request, Long userId) throws BaseException {
        try {
            User user = userRepository.findById(userId)
	.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

            Tumble tumble = Tumble.builder()
	.user(user)
	.menu(request.menu())
	.discountPrice(request.discountPrice())
	.size(Size.from(request.size()))
	.build();

            user.addTumble(tumble);
        } catch (IllegalArgumentException error) {
            throw error;
        } catch (Exception error) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public TumbleDetailResponse detail(Long tumbleId) throws BaseException {
        try {
            Tumble tumble = tumbleRepository.findById(tumbleId)
	.orElseThrow(() -> new BaseException(INVALID_TUMBLE_ID));

            return new TumbleDetailResponse(tumble.getMenu(), tumble.getDiscountPrice(),
	tumble.getSize());
        } catch (Exception error) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
