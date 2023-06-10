package vs.webphotos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
public class MediaService {

    private static final Logger log = LoggerFactory.getLogger(MediaService.class);

    private final StorageService storageService;

    public MediaService(StorageService storageService) {
        this.storageService = storageService;
    }

    public void upload(String username, List<MultipartFile> files) {
        if (username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be blank");
        }
        if (Objects.isNull(files) || files.isEmpty() || files.get(0).isEmpty()) {
            throw new IllegalArgumentException("Files cannot be empty");
        }
        log.info("Files size: {}", files.size());
        log.info("File name: {}", files.get(0).getOriginalFilename());
    }

}
