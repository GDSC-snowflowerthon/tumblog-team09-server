package snowflake.tumblog.tumble.service;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snowflake.tumblog.tumble.domain.Tumble;
import snowflake.tumblog.tumble.dto.TumbleDetailResponse;
import snowflake.tumblog.tumble.dto.CreateTumbleRequest;
import snowflake.tumblog.tumble.repository.TumbleRepository;
import snowflake.tumblog.user.domain.User;
import snowflake.tumblog.user.domain.repository.UserRepository;

@Transactional
@RequiredArgsConstructor
@Service
public class TumbleService {

    private final TumbleRepository tumbleRepository;
    private final UserRepository userRepository;

    public void create(CreateTumbleRequest request) {
        User user = userRepository.findById(request.userId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        Tumble tumble = Tumble.of(user, request);

        user.addTumble(tumble);
    }

    public TumbleDetailResponse detail(Long userId, LocalDate createdAt) {
        Tumble tumble = null;
        try {
            User user = userRepository.findById(userId)
	.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

            tumble = tumbleRepository.findByUserAndCreatedAt(user, createdAt)
	.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 텀블입니다."));
        } catch (IllegalArgumentException e) {
            return new TumbleDetailResponse(null, null, null);
        }
        return new TumbleDetailResponse(tumble.getMenu(), tumble.getDiscountPrice(),
            tumble.getSize());
    }
}
