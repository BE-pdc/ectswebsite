package com.ap.ectswebsite.services;

import com.ap.ectswebsite.dto.*;
import com.ap.ectswebsite.entities.*;
import com.ap.ectswebsite.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Component
public class CachingService {

    private final RestTemplate restTemplate;

    private final ProgramRepository programRepository;

    private final TrajectRepository trajectRepository;

    private final YearRepository yearRepository;

    private final CourseRepository courseRepository;

    private final BlockRepository blockRepository;

    private final String academicYearsURL;

    private final String baseURLNL;

    private final String baseURLENG;

    private final String schoolYear = "2018-19";

    private final BlockEngRepository blockEngRepository;

    private final ProgramEngRepository programEngRepository;



    @Autowired
    public CachingService(RestTemplate restTemplate, ProgramRepository programRepository, TrajectRepository trajectRepository, YearRepository yearRepository, CourseRepository courseRepository, BlockRepository blockRepository, @Value("${academicYearsURL}") String academicYearsURL, @Value("${baseURLNL}")String baseURLNL, @Value("${baseURLENG}")String baseURLENG, BlockEngRepository blockEngRepository, ProgramEngRepository programEngRepository) {
        this.restTemplate = restTemplate;
        this.programRepository = programRepository;
        this.trajectRepository = trajectRepository;
        this.yearRepository = yearRepository;
        this.courseRepository = courseRepository;
        this.blockRepository = blockRepository;
        this.academicYearsURL = academicYearsURL;
        this.baseURLNL = baseURLNL;
        this.baseURLENG = baseURLENG;
        this.blockEngRepository = blockEngRepository;
        this.programEngRepository = programEngRepository;
    }

    /********************
     * fills the database with all programs of a specified year. TODO: change to all years.
     */


    /*
    DUTCH
     */

    public void addYear(String year) {

        //get all programs of a year
        ResponseEntity<List<ProgramDto>> response = restTemplate.exchange(baseURLNL + "/" + year,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProgramDto>>() {
                });
        List<ProgramDto> programs = response.getBody();


        //If you want to show only the first X programs, uncomment the next part, and comment the start of the for.
        int numberOfPrograms = 40;
        //for (int i = 0; i < numberOfPrograms; i++) {
          //  ProgramDto program = programs.get(i);


        for (ProgramDto program : programs) {
            //get all trajects of a program
            ResponseEntity<Set<TrajectDto>> response2 = restTemplate.exchange(baseURLNL + "/" + year + "/" + program.getProgramCode(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Set<TrajectDto>>() {
                    });
            Set<TrajectDto> trajects = response2.getBody();

            //change SpecializationDto to Specialisation entities
            Set<Specialization> specializations = new HashSet<>();

            if (program.getSpecializationDtos() != null) {
                for (SpecializationDto specialization : program.getSpecializationDtos()) {
                    specializations.add(new Specialization(specialization.getSpecilizationcode(), specialization.getName(), specialization.getSpecialization(), year));
                }
            }

            Set<Traject> trajects1 = new TreeSet<>();
            if (trajects != null) {
                for (TrajectDto trajectDto : trajects) {

                    trajects1.add(transformTraject(trajectDto, schoolYear));
                }
            }
            Program toevoegen = new Program(specializations, program.getName(), program.getProgramCode(), trajects1, program.getProgramType(), program.getProgram(), program.getFieldOfStudy(),year);
            programRepository.save(toevoegen);
        }
    }

    public void addAll() {
        Set<String> academieJaren;
        ResponseEntity<Set<String>> responsejaren = restTemplate.exchange(academicYearsURL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Set<String>>() {
                });
        academieJaren = responsejaren.getBody();

        for (String s : academieJaren) {
            this.addYear(s);
        }

    }

    public Traject transformTraject(TrajectDto trajectDto, String schoolYear)
    {
        //for this traject get all years

        Set<Year> years = new HashSet<>();
        if (trajectDto.getYearDtos() != null) {
            for (YearDto yearDto : trajectDto.getYearDtos()) {
                //for each year get all courses and add these to the courseentity
                years.add(transformYearWithCourses(yearDto, schoolYear));
            }
        }
        Traject t = new Traject(years, trajectDto.getName(), trajectDto.getOrde(), trajectDto.getOpltraject(), trajectDto.getSchoolYear());
        return t;
    }

    public Year transformYearWithCourses(YearDto yearDto, String schoolYear)
    {
        Set<Course> courses = new HashSet<>();
        if (yearDto.getCourseDtos() != null) {
            for (CourseDto courseDto : yearDto.getCourseDtos()) {
                Course c = new Course(courseDto.getEcts(), courseDto.getName(), courseDto.getOrde(), courseDto.getStudypoints(), schoolYear);
                courses.add(c);
            }
        }
        //for each year get all blocks
        Set<Block> blocks = new HashSet<>();
        if (yearDto.getBlockDto() != null) {
            //transform all blocks and add them to the blockset
            for (BlockDto blockDto : yearDto.getBlockDto()) {
                blocks.add(transformBlock(blockDto, schoolYear));
            }
        }
        //change yearDTO to year
        Year y = new Year(blocks, yearDto.getName(), courses, yearDto.getOrde(), yearDto.getTraject(), schoolYear);
        return y;
    }

    public Block transformBlock(BlockDto blockDto, String schoolYear)
    {
        //for each block get all courses
        Set<Course> courses1 = new HashSet<>();
        if (blockDto.getCourseDto() != null) {
            for (CourseDto courseDto : blockDto.getCourseDto()) {
                Course c = new Course(courseDto.getEcts(), courseDto.getName(), courseDto.getOrde(), courseDto.getStudypoints(), schoolYear);
                courses1.add(c);
            }
        }
        Block b = new Block(null, blockDto.getNaam(), courses1, blockDto.getOrde(), blockDto.getType(), blockDto.getDescription(), blockDto.getDescriptionOption(), blockDto.getChoiceOption(), blockDto.getChoicePackage(), schoolYear);
        if (!blockRepository.existsByid(b.getId())) {
            blockRepository.save(b);
        }
        return b;
    }

    /*
    THE END OF DUTCH

    ENGLISH
     */

    public void addYearEng(String year) {

        //get all programs of a year
        ResponseEntity<List<ProgramDto>> response = restTemplate.exchange(baseURLENG + "/" + year,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProgramDto>>() {
                });
        List<ProgramDto> programs = response.getBody();


        //If you want to show only the first X programs, uncomment the next part, and comment the start of the for.
        int numberOfPrograms = 20;
        for (int i = 0; i < numberOfPrograms; i++) {
            ProgramDto program = programs.get(i);


        //for (ProgramDto program : programs) {
            //get all trajects of a program
            ResponseEntity<Set<TrajectDto>> response2 = restTemplate.exchange(baseURLENG + "/" + year + "/" + program.getProgramCode(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Set<TrajectDto>>() {
                    });
            Set<TrajectDto> trajects = response2.getBody();

            //change SpecializationDto to Specialisation entities
            Set<SpecializationEng> specializations = new HashSet<>();

            if (program.getSpecializationDtos() != null) {
                for (SpecializationDto specialization : program.getSpecializationDtos()) {
                    specializations.add(new SpecializationEng(specialization.getSpecilizationcode(), specialization.getName(), specialization.getSpecialization(), year));
                }
            }

            Set<TrajectEng> trajects1 = new TreeSet<>();
            if (trajects != null) {
                for (TrajectDto trajectDto : trajects) {

                    trajects1.add(transformTrajectEng(trajectDto, schoolYear));
                }
            }
            ProgramEng toevoegen = new ProgramEng(specializations, program.getName(), program.getProgramCode(), trajects1, program.getProgramType(), program.getProgram(), program.getFieldOfStudy(),year);
            programEngRepository.save(toevoegen);
        }
    }

    public void addAllEng() {
        Set<String> academieJaren;
        ResponseEntity<Set<String>> responsejaren = restTemplate.exchange(academicYearsURL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Set<String>>() {
                });
        academieJaren = responsejaren.getBody();

        for (String s : academieJaren) {
            this.addYearEng(s);
        }

    }



    public BlockEng transformBlockEng(BlockDto blockDto, String schoolYear)
    {
        //for each block get all courses
        Set<CourseEng> courses1 = new HashSet<>();
        if (blockDto.getCourseDto() != null) {
            for (CourseDto courseDto : blockDto.getCourseDto()) {
                CourseEng c = new CourseEng(courseDto.getEcts(), courseDto.getName(), courseDto.getOrde(), courseDto.getStudypoints(), schoolYear);
                courses1.add(c);
            }
        }
        BlockEng b = new BlockEng(null, blockDto.getNaam(), courses1, blockDto.getOrde(), blockDto.getType(), blockDto.getDescription(), blockDto.getDescriptionOption(), blockDto.getChoiceOption(), blockDto.getChoicePackage(), schoolYear);
        if (!blockEngRepository.existsByid(b.getId())) {
            blockEngRepository.save(b);
        }
        return b;
    }

    public YearEng transformYearEngWithCoursesEng(YearDto yearDto, String schoolYear)
    {
        Set<CourseEng> courses = new HashSet<>();
        if (yearDto.getCourseDtos() != null) {
            for (CourseDto courseDto : yearDto.getCourseDtos()) {
                CourseEng c = new CourseEng(courseDto.getEcts(), courseDto.getName(), courseDto.getOrde(), courseDto.getStudypoints(), schoolYear);
                courses.add(c);
            }
        }
        //for each year get all blocks
        Set<BlockEng> blocks = new HashSet<>();
        if (yearDto.getBlockDto() != null) {
            //transform all blocks and add them to the blockset
            for (BlockDto blockDto : yearDto.getBlockDto()) {
                blocks.add(transformBlockEng(blockDto, schoolYear));
            }
        }
        //change yearDTO to year
        YearEng y = new YearEng(blocks, yearDto.getName(), courses, yearDto.getOrde(), yearDto.getTraject(), schoolYear);
        return y;
    }

    public TrajectEng transformTrajectEng(TrajectDto trajectDto, String schoolYear)
    {
        //for this traject get all years

        Set<YearEng> years = new HashSet<>();
        if (trajectDto.getYearDtos() != null) {
            for (YearDto yearDto : trajectDto.getYearDtos()) {
                //for each year get all courses and add these to the courseentity
                years.add(transformYearEngWithCoursesEng(yearDto, schoolYear));
            }
        }
        TrajectEng t = new TrajectEng(years, trajectDto.getName(), trajectDto.getOrde(), trajectDto.getOpltraject(), trajectDto.getSchoolYear());
        return t;
    }







/*****************************************************$
 * De onderstaande code is een mockup voor een recursieve oplossing om blocks in blocks om te kunnen zetten, en dit ongeacht het aantal blocks in blocks.
 * Toekomstbestendig dus. Lukt echter niet dus is momenteel op WACHT gezet.
 */

/*    public Block getBlock(BlockDto blockDto)
    {
        if (blockDto.getBlockDtos() == null)
        {
            Set<Course> courses = new HashSet<>();
            for (CourseDto courseDto : blockDto.getCourseDto())
            {
                courses.add(new Course(courseDto.getEcts(),courseDto.getName(),courseDto.getOrde(),courseDto.getStudypoints()));
            }
            return new Block(null,blockDto.getNaam(),courses, blockDto.getOrde(),blockDto.getType(),blockDto.getDescription(),blockDto.getDescriptionOption(),blockDto.getChoiceOption(),blockDto.getChoicePackage());
        }
        else {
            for (BlockDto blockDto1 : blockDto.getBlockDtos()){
                 getBlock(blockDto1);
            }

        }

    }*/


}
