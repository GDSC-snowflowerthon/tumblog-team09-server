package snowflake.tumblog.tumble.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import snowflake.tumblog.tumble.domain.Tumble;

import java.util.Optional;

@Repository
public interface TumbleRepository extends JpaRepository<Tumble, Long> {
    Optional<Tumble> findByIdAndStatusEquals(Long tumbleId, String activeStatus);
}
