package com.roganin.cv.repository;

import com.roganin.cv.model.Profile;
import com.roganin.cv.model.Skills;

import java.util.List;

public interface CustomProfileRepository {

    void save(Profile profile);

    List<Profile> findAll();

    List<Profile> getProfiles(List<Skills> skills);

}
