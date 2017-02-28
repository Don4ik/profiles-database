package com.roganin.cv.repository;

import com.roganin.cv.model.Profile;
import com.roganin.cv.model.Skills;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomProfileRepository {

    void save(Profile profile);

    List<Profile> findAll();

    List<Profile> getProfiles(List<Skills> skills);

}
