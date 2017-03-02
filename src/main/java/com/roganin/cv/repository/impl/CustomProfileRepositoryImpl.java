package com.roganin.cv.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.roganin.cv.model.Profile;
import com.roganin.cv.model.QProfile;
import com.roganin.cv.model.Skills;
import com.roganin.cv.repository.BasicProfileRepository;
import com.roganin.cv.repository.CustomProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class CustomProfileRepositoryImpl implements CustomProfileRepository {

    private final BasicProfileRepository basicProfileRepository;

    @Autowired
    public CustomProfileRepositoryImpl(BasicProfileRepository basicProfileRepository) {
        this.basicProfileRepository = basicProfileRepository;
    }

    @Override
    public void save(Profile profile) {
        basicProfileRepository.save(profile);
    }

    @Override
    public List<Profile> findAll() {
        return basicProfileRepository.findAll();
    }

    @Override
    public List<Profile> getProfiles(List<Skills> skills) {
        log.info("Creating DB query for skills {}", skills);
        QProfile profile = QProfile.profile;
        BooleanBuilder where = new BooleanBuilder();
        skills.forEach(s -> where.and(profile.skills.contains(s)));
        Iterable<Profile> profiles = basicProfileRepository.findAll(where);
        List<Profile> result = new ArrayList<>();
        profiles.forEach(result::add);

        return result;
    }
}
