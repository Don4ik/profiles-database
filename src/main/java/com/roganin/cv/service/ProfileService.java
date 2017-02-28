package com.roganin.cv.service;


import com.roganin.cv.model.Profile;
import com.roganin.cv.model.Skills;

import java.util.List;

public interface ProfileService {

    void saveProfile(Profile profile);

    List<Profile> getProfiles();

    List<Profile> getProfiles(List<Skills> skills);
}
