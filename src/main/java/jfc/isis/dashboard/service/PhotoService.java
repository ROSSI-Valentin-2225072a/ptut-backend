package jfc.isis.dashboard.service;

import jakarta.transaction.Transactional;
import jfc.isis.dashboard.dao.PhotoRepository;
import jfc.isis.dashboard.entity.Photo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {

    private final PhotoRepository photoDao;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoDao = photoRepository;
    }

    @Transactional
    public Photo savePhoto(String photoBase64, String description) {

        if(photoBase64 == null || photoBase64.isEmpty()) {
            throw new IllegalArgumentException("Photo base64 cannot be empty");
        }
        if(description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }

        var newPhoto = new Photo();
        newPhoto.setPhotoBase64(photoBase64);
        newPhoto.setDescription(description);
        newPhoto.setPhotoLastUse(java.util.Date.from(java.time.Instant.now()));
        photoDao.save(newPhoto);

        return newPhoto;
    }

    @Transactional
    public Photo findPhotoById(Integer photoId) {
        return photoDao.findById(photoId).orElseThrow(() -> new IllegalArgumentException("Photo not found"));
    }

    @Transactional
    public List<Photo> findAllPhotos() {
        return photoDao.findAll();
    }

    @Transactional
    public void deletePhotoById(Integer photoId) {
        photoDao.deleteById(photoId);
    }

    @Transactional
    public Photo updatePhoto(Integer photoId, Optional<String> photoBase64, Optional<String> description) {
        photoDao.deleteById(photoId);

        var photoToUpdate = new Photo();
        photoBase64.ifPresent(photoToUpdate::setPhotoBase64);
        description.ifPresent(photoToUpdate::setDescription);

        return photoDao.save(photoToUpdate);
    }
}
