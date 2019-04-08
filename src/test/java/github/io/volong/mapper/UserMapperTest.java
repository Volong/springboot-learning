package github.io.volong.mapper;

import github.io.volong.domain.Page;
import github.io.volong.domain.UserSexEnum;
import github.io.volong.entity.UserEntity;
import github.io.volong.mapper.one.UserOneMapper;
import github.io.volong.mapper.two.UserTwoMapper;
import github.io.volong.param.UserParam;
import org.junit.Assert;
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
    private UserOneMapper userOneMapper;

    @Autowired
    private UserTwoMapper userTwoMapper;

    @Test
    public void testInsert() {
        userOneMapper.insert(new UserEntity("yi", "yi123456", UserSexEnum.WOMAN));
        userTwoMapper.insert(new UserEntity("hai", "hai123456", UserSexEnum.MAN));

        Assert.assertFalse(userOneMapper.getAll().isEmpty());
        Assert.assertFalse(userTwoMapper.getAll().isEmpty());
    }
}
