package vs.webphotos;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("media")
public class MediaController {

    private final MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping("plans")
    public Set<PricePlan> plans() {
        return EnumSet.allOf(PricePlan.class);
    }

    @GetMapping("capacity/{username}")
    public Long usedCapacity(@PathVariable String username) throws IOException {
        return mediaService.usedCapacity(username);
    }

    @PostMapping("upload")
    public void upload(@RequestParam("username") String username,
                       @RequestParam("files") List<MultipartFile> files) throws IOException {
        mediaService.upload(username, files);
    }

}
