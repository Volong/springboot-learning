package github.io.volong.mapper;

import github.io.volong.domain.Page;
import github.io.volong.domain.UserSexEnum;
import github.io.volong.entity.UserEntity;
import github.io.volong.param.UserParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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

    @Test
    public void testPage() {
        UserParam userParam = new UserParam();
        userParam.setUserSex(UserSexEnum.MAN.name());
        userParam.setCurrentPage(1);

        List<UserEntity> users = userMapper.getList(userParam);
        int count = userMapper.getCount(userParam);

        Page page = new Page(userParam, count, users);
        System.out.println(page);
    }
}
