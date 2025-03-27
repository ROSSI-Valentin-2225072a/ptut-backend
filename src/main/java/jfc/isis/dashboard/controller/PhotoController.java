package jfc.isis.dashboard.controller;

import jfc.isis.dashboard.DTO.ApiErrorDTO;
import jfc.isis.dashboard.DTO.PhotoDTO;
import jfc.isis.dashboard.service.PhotoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/dashboard/photo")
public class PhotoController {

    private final PhotoService photoService;
    private final ModelMapper mapper;

    public PhotoController(PhotoService photoService, ModelMapper mapper) {
        this.photoService = photoService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> savePhoto(
            @RequestParam String photoUrl,
            @RequestParam String description) {
        try {
            var dashboard = photoService.savePhoto(photoUrl, description);
            var body = mapper.map(dashboard, PhotoDTO.class);
            return ResponseEntity.ok(body);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("An error occurred: " + e.getMessage()));
        }
    }

    @GetMapping("id={photoId}")
    public ResponseEntity<?> getPhotoById(
            @PathVariable Integer photoId) {
        try {
            var dashboard = photoService.findPhotoById(photoId);
            var body = mapper.map(dashboard, PhotoDTO.class);
            return ResponseEntity.ok(body);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("An error occurred: " + e.getMessage()));
        }
    }

    @GetMapping("/description={description}")
    public ResponseEntity<?> getPhotoByDescription(
            @PathVariable String description) {
        try {
            var dashboard = photoService.findPhotoByDescription(description);
            var body = mapper.map(dashboard, PhotoDTO.class);
            return ResponseEntity.ok(body);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("An error occurred: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllPhotos() {
        try {
            var dashboard = photoService.findAllPhotos();
            var body = mapper.map(dashboard, PhotoDTO.class);
            return ResponseEntity.ok(body);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("An error occurred: " + e.getMessage()));
        }
    }

}
