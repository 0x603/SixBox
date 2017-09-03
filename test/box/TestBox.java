package box;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import mongo.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sos.sixbox.box.repository.FileRepository;
import org.sos.sixbox.box.service.BoxService;
import org.sos.sixbox.entity.FileEntity;
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
public class TestBox {

    @Autowired
    UserService userService;
    @Autowired
    BoxService boxService;
    @Autowired
    FileRepository fileRepository;

    @Test
    public void testUploadFile() throws IOException {
        // 创建测试文件
        int someone = 1000;
        File uploadFile = FileUtils.createTestFile();

        // 文件实体
        FileEntity fileEntity = new FileEntity();
        fileEntity.setOwnerId(someone);

        // 文件元信息
        DBObject metaData = new BasicDBObject();
        metaData.put("info", "testUploadFile");

        // 上传文件
        int fileCountBeforeUpload = fileRepository.findAllByOwnerId(someone).size();
        boxService.upload(fileEntity, uploadFile, metaData);
        int fileCountAfterUpload = fileRepository.findAllByOwnerId(someone).size();
        Assert.assertEquals(fileCountBeforeUpload + 1, fileCountAfterUpload);
        fileRepository.deleteAllByOwnerId(someone);
        int fileCountAfterDelete = fileRepository.findAllByOwnerId(someone).size();
        Assert.assertEquals(fileCountAfterDelete, 0);

        // 删除测试文件
        FileUtils.removeTestFile();
    }
}
