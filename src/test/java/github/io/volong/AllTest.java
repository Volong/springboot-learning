package github.io.volong;

import github.io.volong.controller.UserControllerTest;
import github.io.volong.service.UserServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @time 2019-04-16
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        HelloTest.class,
        UserServiceTest.class,
        UserControllerTest.class
})
public class AllTest {

}
