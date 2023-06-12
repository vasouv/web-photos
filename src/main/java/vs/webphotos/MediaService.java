package vs.webphotos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vs.webphotos.exception.NotEnoughCapacityException;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class MediaService {

    private static final Logger log = LoggerFactory.getLogger(MediaService.class);

    private final StorageService storageService;

    public MediaService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Long usedCapacity(String username) throws IOException {
        return storageService.usedCapacity(username);
    }

    public void upload(String username, List<MultipartFile> files) throws IOException {
        if (username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be blank");
        }
        if (Objects.isNull(files) || files.isEmpty() || files.get(0).isEmpty()) {
            throw new IllegalArgumentException("Files cannot be empty");
        }

        // TODO: get price plan for each user from database
        var userPlan = PricePlan.FREE;

        // check if there is any non-photo file
        // TODO: according to plan, check for videos
        files.forEach(f -> {
            if (!Set.of(MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE).contains(f.getContentType())) {
                throw new IllegalArgumentException("Cannot upload: %s with content type: %s".formatted(f.getOriginalFilename(), f.getContentType()));
            }
        });

        Long uploadFileSize = files.stream().map(MultipartFile::getSize).reduce(0L, Long::sum);

        Long currentUsedCapacity = storageService.usedCapacity(username);

        if (userPlan.hasAvailableCapacity(uploadFileSize + currentUsedCapacity)) {
            log.info("Files can be uploaded");
            storageService.saveFiles(username, files);
        } else {
            log.error("Not available capacity");
            throw new NotEnoughCapacityException("Not available capacity");
        }

    }

}
