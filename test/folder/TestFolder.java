package folder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sos.sixbox.box.repository.FileRepository;
import org.sos.sixbox.entity.FileEntity;
import org.sos.sixbox.entity.FolderEntity;
import org.sos.sixbox.folder.repository.FolderRepository;
import org.sos.sixbox.folder.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * Created by Lodour on 2017/9/1 21:44.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class TestFolder {

    @Autowired
    FileRepository fileRepository;
    @Autowired
    FolderService folderService;
    @Autowired
    FolderRepository folderRepository;

    @Test
    public void testInit() throws IOException {
        // Init
        folderService.initSystemFolder();
//        folderService.initUserFolder(1);

        // ls /
        tree(folderRepository.getRootFolder().getId(), null);
    }

    private void tree(String rootId, String indent) {
        FolderEntity root = folderRepository.findById(rootId);
        // Root
        if (indent == null) {
            indent = "";
            System.out.println(root.getName());
        }
        indent += "  ";
        // SubFolders
        for (FolderEntity child : folderService.getSubFolders(rootId)) {
            System.out.println(indent + child.getName() + "/");
            tree(child.getId(), indent);
        }
        // Files
        for (FileEntity file : folderService.getFiles(rootId)) {
            System.out.println(indent + file.getFilename());
        }
    }
}
