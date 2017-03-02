package com.roganin.cv.controller;

import com.roganin.cv.model.Profile;
import com.roganin.cv.model.Skills;
import com.roganin.cv.service.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/")
@Slf4j
public class ProfileController {

    private final ProfileService profileService;

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

    public static void main(String[] args) {
        System.out.println(Skills.valueOf("javaw".toUpperCase()));
    }

    @RequestMapping(method = GET)
    public List<Profile> getProfiles(@RequestParam(value = "skills", required = false) List<String> skills) {
        if (skills != null && skills.size() != 0) {
            List<Skills> skillsList = new ArrayList<>();
            skills.forEach(s -> skillsList.add(Skills.valueOf(s.toUpperCase())));
            log.info("Processing request for skills: {}", skillsList);
            return profileService.getProfiles(skillsList);
        }
        log.info("Processing request for all available profiles");
        return profileService.getProfiles();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public void handleIllegalArgumentException(HttpServletResponse response) {
        response.setStatus(400);
    }
}
