package com.roganin.cv.controller;

import com.roganin.cv.model.Profile;
import com.roganin.cv.model.Skills;
import com.roganin.cv.service.ProfileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProfileController.class)
public class ProfileControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProfileService profileService;

    @Test
    public void profileCreatedSuccessfully() throws Exception {
        // Given

        // When
        mvc.perform(post("/").content(
                "{\n" +
                        "    \"id\": 1,\n" +
                        "    \"name\": \"John1\",\n" +
                        "    \"age\": 25,\n" +
                        "    \"skills\": [\n" +
                        "      \"java\",\n" +
                        "      \"python\"\n" +
                        "    ]\n" +
                        "  }")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201));

        // Then
        // Test passed

    }

    @Test
    public void badRequestOnInvalidRequestBody() throws Exception {
        // Given

        // When
        mvc.perform(post("/").content("test")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));

        // Then
        // Test passed

    }

    @Test
    public void listsAllProfiles() throws Exception {
        // Given
        Profile profile1 = new Profile();
        profile1.setName("P1");
        Profile profile2 = new Profile();
        profile2.setName("P2");

        List<Profile> profiles = new ArrayList<>();
        profiles.add(profile1);
        profiles.add(profile2);

        when(profileService.getProfiles()).thenReturn(profiles);


        // When
        mvc.perform(get("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.*", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("P1")))
                .andExpect(jsonPath("$[1].name", is("P2")));

        // Then
        // Test passed

    }

    @Test
    public void listsProfilesBySkills() throws Exception {
        // Given
        Profile profile1 = new Profile();
        profile1.getSkills().add(Skills.JAVA);
        profile1.getSkills().add(Skills.PYTHON);
        profile1.setName("P1");

        List<Profile> profiles = new ArrayList<>();
        profiles.add(profile1);
        List<Skills> skillsList = new ArrayList<Skills>();
        skillsList.add(Skills.JAVA);
        skillsList.add(Skills.PYTHON);
        when(profileService.getProfiles(skillsList)).thenReturn(profiles);

        // When
        mvc.perform(get("/?skills=java&skills=python")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("P1")));

        // Then
        // Test passed

    }


    @Test
    public void badRequestOnInvalidSkillName() throws Exception {
        // Given


        // When
        mvc.perform(get("/?skills=javaa")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));

        // Then
        // Test passed

    }


}

