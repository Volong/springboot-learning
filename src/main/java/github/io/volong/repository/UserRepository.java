package github.io.volong.repository;

import github.io.volong.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * dao 只要继承 JpaRepository 类就可以，几乎可以不用写方法.
 * 还有一个功能非常赞，就是可以根据方法名来自动的生产 SQL，
 * 比如 findByUserName 会自动生产一个以 userName 为参数的查询方法，
 * 比如 findAll 自动会查询表里面的所有数据，比如自动分页等。
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u")
    Page<User> findList(Pageable pageable);

    User findById(long id);

    User findByUserName(String userName);

    Long deleteById(Long id);
}