package com.roganin.cv.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.roganin.cv.model.Profile;
import com.roganin.cv.model.QProfile;
import com.roganin.cv.model.Skills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CustomProfileRepositoryImpl implements CustomProfileRepository {

    @PersistenceContext
    private EntityManager em;

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
        JPAQuery<Profile> query = new JPAQuery<>(em);
        QProfile profile = QProfile.profile;
        query = query.from(profile);
        for (Skills s : skills) {
            query = query.where(profile.skills.contains(s));
        }

        return query.fetch();
    }
}
