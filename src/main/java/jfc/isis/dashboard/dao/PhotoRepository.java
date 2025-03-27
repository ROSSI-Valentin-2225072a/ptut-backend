package jfc.isis.dashboard.dao;

import jfc.isis.dashboard.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    @Query("""
        SELECT p.photoId, p.photoUrl, p.photoLastUse, p.description
        FROM Photo p
        WHERE p.photoId = :photoId
    """)
    Photo findByPhotoId(int photoId);

    @Query("""
        SELECT p.photoId, p.photoUrl, p.photoLastUse, p.description
        FROM Photo p
        WHERE p.description = :description
    """)
    Photo findByDescription(String description);

}
