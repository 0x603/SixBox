package user;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sos.sixbox.entity.UserEntity;
import org.sos.sixbox.user.service.UserService;
import org.sos.sixbox.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;

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

    @Test
    public void loginTest() {
        UserEntity userEntity = null;
        // Before login
        userEntity = userService.getById(1);
        Timestamp timeBeforeLogin = userEntity.getLastLoginTime();
        String ipBeforeLogin = userEntity.getLastLoginIp();
        // Login
        userService.loginUser(userEntity.getUsername(), userEntity.getPassword(), ipBeforeLogin + "##");
        // After login
        userEntity = userService.getById(1);
        Timestamp timeAfterLogin = userEntity.getLastLoginTime();
        String ipAfterLogin = userEntity.getLastLoginIp();
        // Check
        System.out.println(timeBeforeLogin + ", " + timeAfterLogin);
        Assert.assertNotEquals(timeBeforeLogin, timeAfterLogin);
        Assert.assertNotEquals(ipBeforeLogin, ipAfterLogin);
    }
}