package github.io.volong.mapper;

import github.io.volong.domain.UserSexEnum;
import github.io.volong.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @time 2019-04-02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUser() {
        userMapper.insert(new UserEntity("aa", "111111", UserSexEnum.MAN));


        userMapper.delete(1L);
    }
}
