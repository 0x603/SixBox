package user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sos.sixbox.entity.UserEntity;
import org.sos.sixbox.user.service.UserService;
import org.sos.sixbox.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Lodour on 2017/8/18 23:49.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class TestUserService {
    @Autowired
    private UserService userService;

    @Test
    public void createTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("test:" + Utils.getCurrentTimestamp());
        userEntity.setPassword("pass");
        userService.createUser(userEntity, "localhost");
    }
}
