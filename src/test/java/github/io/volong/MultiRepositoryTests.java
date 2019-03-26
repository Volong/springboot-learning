package github.io.volong;


import github.io.volong.domain.User;
import github.io.volong.repository.database1.UserTest1Repository;
import github.io.volong.repository.database2.UserTest2Repository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MultiRepositoryTests {

    @Autowired
    private UserTest1Repository userTest1Repository;

    @Autowired
    private UserTest2Repository userTest2Repository;

    @Test
    public void testSave() throws Exception {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        userTest1Repository.save(new User("aa", "aa123456","aa@126.com", "aa",  formattedDate));
        userTest1Repository.save(new User("bb", "bb123456","bb@126.com", "bb",  formattedDate));
        userTest2Repository.save(new User("cc", "cc123456","cc@126.com", "cc",  formattedDate));

    }
}
