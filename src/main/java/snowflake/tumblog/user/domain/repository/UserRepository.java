package snowflake.tumblog.user.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import snowflake.tumblog.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
