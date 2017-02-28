package com.roganin.cv.repository;


import com.roganin.cv.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicProfileRepository extends JpaRepository<Profile, Long> {

}
