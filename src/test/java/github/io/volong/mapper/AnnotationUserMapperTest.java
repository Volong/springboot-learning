package github.io.volong.mapper;

import github.io.volong.domain.UserSexEnum;
import github.io.volong.entity.UserEntity;
import github.io.volong.mapper.annotation.AnnotationUserMapper;
import github.io.volong.param.UserParam;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sound.midi.Soundbank;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnnotationUserMapperTest {

    @Autowired
    private AnnotationUserMapper annotationUserMapper;

    @Test
    public void testGetAll() {
        List<UserEntity> all = annotationUserMapper.getAll();
        System.out.println(all);
    }

    @Test
    public void testGetOne() {
        UserEntity one = annotationUserMapper.getOneById(2L);
        Assert.assertNotNull(one);
    }

    @Test
    public void testInsert() {
        UserEntity yi = new UserEntity("yi", "123456", UserSexEnum.MAN);
        Long insert = annotationUserMapper.insert(yi);
        System.out.println(yi.getId());
        Assert.assertNotNull(yi.getId());
    }

    @Test
    public void testDelete() {
        int delete = annotationUserMapper.delete(11L);
        System.out.println(delete);
        Assert.assertTrue( delete > 0);
    }

    @Test
    public void testGetList() {
        UserParam userParam = new UserParam();
        userParam.setUserName("yi");

        List<UserEntity> list = annotationUserMapper.getList(userParam);
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void testGetCount() {
        UserParam userParam = new UserParam();
        userParam.setUserName("yi");

        int count = annotationUserMapper.getCount(userParam);
        System.out.println("count:" + count);
        Assert.assertTrue(count > 0);
    }
}
