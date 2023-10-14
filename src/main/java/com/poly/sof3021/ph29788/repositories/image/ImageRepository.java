package com.poly.sof3021.ph29788.repositories.image;

import com.poly.sof3021.ph29788.entities.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
