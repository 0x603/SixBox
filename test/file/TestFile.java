package file;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import mongo.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sos.sixbox.entity.FileEntity;
import org.sos.sixbox.file.service.FileService;
import org.sos.sixbox.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;

/**
 * Created by Lodour on 2017/9/1 21:44.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class TestFile {

    @Autowired
    FileService fileService;

    @Autowired
    UserService userService;

    @Test
    public void testUploadFile() throws IOException {
        // 创建测试文件
        File uploadFile = FileUtils.createTestFile();

        // 文件实体
        FileEntity fileEntity = new FileEntity();
        fileEntity.setOwner(userService.getById(1));

        // 文件元信息
        DBObject metaData = new BasicDBObject();
        metaData.put("info", "testUploadFile");

        // 上传文件
        fileService.upload(fileEntity, uploadFile, metaData);

        // 删除测试文件
        FileUtils.removeTestFile();
    }
}
