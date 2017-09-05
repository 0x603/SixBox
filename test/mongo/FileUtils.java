package mongo;

import org.junit.Assert;
import org.sos.sixbox.utils.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Lodour on 2017/8/28 08:39.
 */
public class FileUtils {
    /**
     * 测试文件名
     */
    public static String testFileName = "test.txt";

    /**
     * 创建测试文件
     */
    public static File createTestFile() throws IOException {
        FileWriter fileWriter = new FileWriter(testFileName);
        fileWriter.write("Create at " + Utils.getCurrentTimestamp());
        fileWriter.close();
        return new File(testFileName);
    }

    /**
     * 删除测试文件
     */
    public static void removeTestFile() {
        File file = new File(testFileName);
        if (file.exists() && file.isFile()) {
            Assert.assertTrue(file.delete());
        }
    }
}
