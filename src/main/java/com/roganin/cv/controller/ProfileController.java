package com.roganin.cv.controller;

import com.roganin.cv.model.Profile;
import com.roganin.cv.model.Skills;
import com.roganin.cv.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/")
public class ProfileController {

    private final ProfileService profileService;

    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    public void saveProfile(@RequestBody Profile profile) {
        log.info("Received profile: {}", profile);
        profileService.saveProfile(profile);
    }

    @RequestMapping(method = GET)
    public List<Profile> getProfile1(@RequestParam(value = "skills", required = false) List<String> skills) {
        if (skills != null && skills.size() != 0) {
            List<Skills> skillList = validateSkills(skills);
            if (skillList.size() == 0) {
                throw new IllegalArgumentException();
            }
            return profileService.getProfiles(skillList);
        }
        return profileService.getProfiles();
    }

    private List<Skills> validateSkills(List<String> skills) {
        List<Skills> skillList = new ArrayList<>();
        skills.forEach(skill -> stream(Skills.values()).filter(v -> v.name().equalsIgnoreCase(skill)).forEach(skillList::add));
        return skillList;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public void handleIllegalArgumentException(HttpServletResponse response) {
        response.setStatus(400);
    }
}
