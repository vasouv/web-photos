package vs.webphotos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("media")
public class MediaController {

    private static final Logger log = LoggerFactory.getLogger(MediaController.class);

    private final MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @PostMapping("upload")
    public void upload(@RequestParam("username") String username, @RequestParam("files") List<MultipartFile> files) {
        mediaService.upload(username, files);
    }

}
