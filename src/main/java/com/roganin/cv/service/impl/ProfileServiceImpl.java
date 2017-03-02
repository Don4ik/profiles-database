package com.roganin.cv.service.impl;


import com.roganin.cv.model.Profile;
import com.roganin.cv.model.Skills;
import com.roganin.cv.repository.CustomProfileRepository;
import com.roganin.cv.service.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProfileServiceImpl implements ProfileService {

    private final CustomProfileRepository customProfileRepository;

    @Autowired
    public ProfileServiceImpl(CustomProfileRepository customProfileRepository) {
        this.customProfileRepository = customProfileRepository;
    }

    @Override
    public void saveProfile(Profile profile) {
        log.info("Processing profile: {}", profile);
        customProfileRepository.save(profile);
    }

    @Override
    public List<Profile> getProfiles() {
        return customProfileRepository.findAll();
    }

    @Override
    public List<Profile> getProfiles(List<Skills> skills) {
        return customProfileRepository.getProfiles(skills);
    }
}
