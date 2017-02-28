package com.roganin.cv.repository;

import com.roganin.cv.Bootstrap;
import com.roganin.cv.model.Profile;
import com.roganin.cv.model.Skills;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static com.roganin.cv.model.Skills.JAVA;
import static com.roganin.cv.model.Skills.JS;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Bootstrap.class)
public class CustomProfileRepositoryImplIT {

    @Autowired
    private CustomProfileRepository customProfileRepository;

    @Autowired
    private BasicProfileRepository basicProfileRepository;

    @Test
    public void fetchesProfilesBySkills() {
        // Given
        Profile profile1 = new Profile();
        profile1.setName("P1");
        profile1.getSkills().add(JAVA);
        profile1.getSkills().add(JS);
        basicProfileRepository.save(profile1);
        List<Skills> skills = new ArrayList<>();
        skills.add(JAVA);
        skills.add(JS);

        // When
        List<Profile> result = customProfileRepository.getProfiles(skills);

        // Then
        assertThat(result.size(), is(1));
        Profile resultProfile = result.iterator().next();
        assertThat(resultProfile.getName(), is("P1"));
    }

}
