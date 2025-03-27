package jfc.isis.dashboard.service;

import jakarta.transaction.Transactional;
import jfc.isis.dashboard.dao.PhotoRepository;
import jfc.isis.dashboard.entity.Photo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {

    private final PhotoRepository photoDao;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoDao = photoRepository;
    }

    @Transactional
    public Photo savePhoto(String photoUrl, String description) {
        var photoExists = photoDao.findByDescription(description);

        if(photoUrl == null || photoUrl.isEmpty()) {
            throw new IllegalArgumentException("Photo URL cannot be empty");
        }
        if(description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if(photoExists != null) {
            throw new IllegalArgumentException("Photo already exists");
        }

        var newPhoto = new Photo();
        newPhoto.setPhotoUrl(photoUrl);
        newPhoto.setDescription(description);
        photoDao.save(newPhoto);

        return newPhoto;
    }

    @Transactional
    public Photo findPhotoById(Integer photoId) {
        return photoDao.findById(photoId).orElseThrow(() -> new IllegalArgumentException("Photo not found"));
    }

    @Transactional
    public Photo findPhotoByDescription(String description) {
        return photoDao.findByDescription(description);
    }

    @Transactional
    public List<Photo> findAllPhotos() {
        return photoDao.findAll();
    }
}
