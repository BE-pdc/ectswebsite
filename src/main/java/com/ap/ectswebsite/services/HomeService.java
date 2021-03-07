package com.ap.ectswebsite.services;

import com.ap.ectswebsite.dto.TrajectDto;
import com.ap.ectswebsite.entities.Program;
import com.ap.ectswebsite.entities.Specialization;
import com.ap.ectswebsite.entities.Traject;
import com.ap.ectswebsite.entities.Year;
import com.ap.ectswebsite.repositories.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class HomeService {

    private final String academicYearsURL;

    private final String baseURLNL;

    private final String baseURLENG;

    private final RestTemplate restTemplate;

    @Autowired
    public HomeService(RestTemplate restTemplate, @Value("${academicYearsURL}")String academicYearsURL, @Value("${baseURLNL}")String baseURLNL, @Value("${baseURLENG}")String baseURLENG) {
        this.academicYearsURL = academicYearsURL;
        this.baseURLNL = baseURLNL;
        this.baseURLENG = baseURLENG;
        this.restTemplate = restTemplate;
    }

    public List<String> getAcademiejaren() throws IOException {
        //Retrieves all academic years from API as JSON and maps to Set<String>
        ResponseEntity<List<String>> response = restTemplate.exchange(academicYearsURL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<String>>() {
                });
        List<String> academieJaren = response.getBody();
        Collections.reverse( academieJaren);
        return academieJaren;
    }

    /*public Set<TrajectDto> getTrajectsOfSpecialization(String schoolYear, String programCode, String language, int p_opltraject) throws IOException {

        String defaultSchoolYear = "2018-19";    // should be deleted later when we have all years in database

        // Retrieves cached programms
        Iterable<Program> programs = programRepository.findAll();
        Set<TrajectDto> trajectList = new HashSet<>();

        // Retrieves specializations of program
        Set<Specialization> specializations = null;
        for (Program p : programs) {
            if (p.getProgramCode().equals(programCode)) {
                specializations = p.getSpecializations();
            }
        }

        for (Specialization s : specializations) {
            ResponseEntity<Set<TrajectDto>> response;

            if (language.equals("nl")) {
                //Retrieves all specializations from API in Dutch as JSON and maps to Set<TrajectDto>
                response = restTemplate.exchange(baseURLNL + defaultSchoolYear + "/" + programCode + "/" + s.getSpecilizationcode(),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<Set<TrajectDto>>() {
                        });
            } else {
                //Retrieves all specializations from API in English as JSON and maps to Set<TrajectDto>
                response = restTemplate.exchange(baseURLENG + defaultSchoolYear + "/" + programCode + "/" + s.getSpecilizationcode(),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<Set<TrajectDto>>() {
                        });
            }

            trajectList.addAll(response.getBody());
        }

        Set<TrajectDto> trajects = new HashSet<>(trajectList);

        //Removes specializations of other trajects
        for (TrajectDto n : trajectList) {
            if (n.getOpltraject()!= p_opltraject)
                trajects.remove(n);
            }


        return trajects;
    }*/
}
