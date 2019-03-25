package github.io.volong.repository;

import github.io.volong.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * dao 只要继承 JpaRepository 类就可以，几乎可以不用写方法.
 * 还有一个功能非常赞，就是可以根据方法名来自动的生产 SQL，
 * 比如 findByUserName 会自动生产一个以 userName 为参数的查询方法，
 * 比如 findAll 自动会查询表里面的所有数据，比如自动分页等。
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

    User findByUserNameOrEmail(String username, String email);

    @Query("select u from User u")
    Page<User> findALL(Pageable pageable);

    Page<User> findByNickName(String nickName, Pageable pageable);


    /*
     *****************
       自定义 SQL 查询
     *****************
     *
     * SQL 语句为 HQL，需要写类的名字跟属性
     *
     */


    @Modifying     // 删除和修改需要加上该注解
    @Transactional(timeout = 10)
    @Query("update User set userName = ?1 where id = ?2")
    int modifyById(String  userName, Long id);

    @Modifying
    @Transactional
    @Query("delete from User where id = ?1")
    void deleteById(Long id);

    @Query("select u from User u where u.email = ?1")
    User findByEmail(String email);
}