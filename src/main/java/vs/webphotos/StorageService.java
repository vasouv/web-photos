package vs.webphotos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
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

    public void createUserDirectory(String username) throws IOException {
        Path userDirectory = Paths.get(ROOT, WEB_PHOTOS, username);
        Files.createDirectories(userDirectory);
    }

    public Long usedCapacity(String username) throws IOException {
        Path userDirectory = Paths.get(ROOT, WEB_PHOTOS, username);
        try (var fileStream = Files.walk(userDirectory, 1)) {
            return fileStream
                    .map(Path::toFile)
                    .filter(File::isFile)
                    .map(File::length)
                    .reduce(0L, Long::sum);
        }
    }

}
