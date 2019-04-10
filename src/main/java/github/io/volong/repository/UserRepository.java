package github.io.volong.repository;

import github.io.volong.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @time 2019-04-10
 */
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByNickname(String nickname);
}
