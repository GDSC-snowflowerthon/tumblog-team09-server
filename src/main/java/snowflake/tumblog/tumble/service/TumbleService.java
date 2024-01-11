package snowflake.tumblog.tumble.service;

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

    public void create(CreateTumbleRequest request, Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        Tumble tumble = Tumble.of(user, request);

        user.addTumble(tumble);
    }

    public TumbleDetailResponse detail(Long tumbleId) {
        Tumble tumble = tumbleRepository.findById(tumbleId)
            .orElseThrow(() -> new IllegalArgumentException());

        return new TumbleDetailResponse(tumble.getMenu(), tumble.getDiscountPrice(),
            tumble.getSize());
    }
}
