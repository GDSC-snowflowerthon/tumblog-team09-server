package snowflake.tumblog.tumble.repository;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import snowflake.tumblog.tumble.domain.Tumble;
import snowflake.tumblog.user.domain.User;

@Repository
public interface TumbleRepository extends JpaRepository<Tumble, Long> {
    Optional<Tumble> findByUserAndCreatedAt(User user, LocalDate createdAt);
}
