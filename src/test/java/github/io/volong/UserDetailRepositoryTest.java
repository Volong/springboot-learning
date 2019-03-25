package github.io.volong;

import github.io.volong.domain.UserDetail;
import github.io.volong.domain.UserInfo;
import github.io.volong.repository.UserDetailRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDetailRepositoryTest {

    @Resource
    private UserDetailRepository userDetailRepository;

    /**
     * 多表查询: 使用一个接口来接收连表查询后的结果
     */
    @Test
    public void testUserInfo() {

        UserDetail userDetail = new UserDetail();
        userDetail.setAddress("xxxx");
        userDetail.setHobby("打球");
        userDetail.setUserId("5");
        userDetailRepository.save(userDetail);
        List<UserInfo> userInfos=userDetailRepository.findUserInfo("打球");
        for (UserInfo userInfo:userInfos){
            System.out.println("addree "+userInfo.getAddress());
        }
    }
}
