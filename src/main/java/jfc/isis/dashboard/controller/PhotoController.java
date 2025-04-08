package jfc.isis.dashboard.controller;

import jakarta.validation.constraints.Positive;
import jfc.isis.dashboard.DTO.ApiErrorDTO;
import jfc.isis.dashboard.DTO.PhotoDTO;
import jfc.isis.dashboard.DTO.QuoteDTO;
import jfc.isis.dashboard.service.PhotoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
            @RequestBody PhotoDTO newPhoto) {
        try {
            var dashboard = photoService.savePhoto(newPhoto.getPhotoUrl(), newPhoto.getDescription());
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
            @PathVariable @Positive Integer photoId) {
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

    @GetMapping
    public ResponseEntity<?> getAllPhotos() {
        try {
            var result = photoService.findAllPhotos();
            var body = result.stream()
                    .map(d -> mapper.map(d, PhotoDTO.class))
                    .toList();
            return ResponseEntity.ok(body);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("An error occurred: " + e.getMessage()));
        }
    }

    @DeleteMapping("id={photoId}")
    public ResponseEntity<?> deletePhotoById(
            @PathVariable @Positive Integer photoId) {
        try {
            photoService.deletePhotoById(photoId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("An error occurred: " + e.getMessage()));
        }
    }

    @PutMapping("id={photoId}")
    public ResponseEntity<?> updatePhoto(
            @PathVariable @Positive Integer photoId,
            @RequestBody PhotoDTO updatedPhoto) {
        try {
            var dashboard = photoService.updatePhoto(photoId, Optional.of(updatedPhoto.getPhotoUrl()), Optional.of(updatedPhoto.getDescription()));
            var body = mapper.map(dashboard, PhotoDTO.class);
            return ResponseEntity.ok(body);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("An error occurred: " + e.getMessage()));
        }
    }

}
