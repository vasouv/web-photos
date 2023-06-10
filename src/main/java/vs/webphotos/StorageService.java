package vs.webphotos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageService {

    private static final Logger log = LoggerFactory.getLogger(StorageService.class);

    private static final String ROOT = System.getProperty("user.home");
    private static final String WEB_PHOTOS = "web_photos";

    public boolean userDirectoryExists(String username) {
        Path userDirectory = Paths.get(ROOT, WEB_PHOTOS, username);
        boolean exists = Files.exists(userDirectory);
        log.info("Directory: {} exists: {}", userDirectory.toFile().getAbsolutePath(), exists);
        return exists;
    }

}
