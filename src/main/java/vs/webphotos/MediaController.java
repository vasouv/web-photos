package vs.webphotos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.EnumSet;
import java.util.List;

@RestController
@RequestMapping("media")
public class MediaController {

    private static final Logger log = LoggerFactory.getLogger(MediaController.class);

    private final MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping("plans")
    public EnumSet<PricePlan> plans() {
        return EnumSet.allOf(PricePlan.class);
    }

    @PostMapping("upload")
    public void upload(@RequestParam("username") String username, @RequestParam("files") List<MultipartFile> files) throws IOException {
        mediaService.upload(username, files);
    }

}
