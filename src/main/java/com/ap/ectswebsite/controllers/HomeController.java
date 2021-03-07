package com.ap.ectswebsite.controllers;

import com.ap.ectswebsite.entities.*;
import com.ap.ectswebsite.exceptions.AcademiejarenNotFoundException;
import com.ap.ectswebsite.exceptions.DeelTrajectNotFoundException;
import com.ap.ectswebsite.exceptions.OpleidingTrajectNotFoundException;
import com.ap.ectswebsite.exceptions.ProgramCodeNotFoundException;
import com.ap.ectswebsite.repositories.*;
import com.ap.ectswebsite.services.CachingService;
import com.ap.ectswebsite.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/")
@Validated
public class HomeController {

    private final HomeService homeService;

    private final CachingService cachingService;

    private final ProgramRepository programRepository;

    private final TrajectRepository trajectRepository;

    private final YearRepository yearRepository;

    private final ProgramEngRepository programEngRepository;

    private final TrajectEngRepository trajectEngRepository;

    private final YearEngRepository yearEngRepository;

    @Autowired
    public HomeController(HomeService homeService, CachingService cachingService, ProgramRepository programRepository, TrajectRepository trajectRepository, YearRepository yearRepository, ProgramEngRepository programEngRepository, TrajectEngRepository trajectEngRepository, YearEngRepository yearEngRepository) {
        this.homeService = homeService;
        this.cachingService = cachingService;
        this.programRepository = programRepository;
        this.trajectRepository = trajectRepository;
        this.yearRepository = yearRepository;
        this.programEngRepository = programEngRepository;
        this.trajectEngRepository = trajectEngRepository;
        this.yearEngRepository = yearEngRepository;
    }

    //Redirect from homepage to /opleidingen/2018-19
    @GetMapping("/")
    public ModelAndView Index() {
        return new ModelAndView("redirect:/opleidingen/2018-19");
    }

    //Refill the database, and then redirect user to opeidingen homepage
    @GetMapping("/refreshcache")
    public ModelAndView RefreshCache() {

        cachingService.addAll();
        cachingService.addAllEng();
        return new ModelAndView("redirect:/opleidingen/2018-19");
    }

    //adds two years to the cache for dev purposes
    @GetMapping("/cachetest")
    public ModelAndView CacheTest() {

        cachingService.addYear("2018-19");
        cachingService.addYearEng("2018-19");
        return new ModelAndView("redirect:/opleidingen/2018-19");
    }
    //caching the request schoolYear, for dev purposes
    @GetMapping("/cache/{schoolYear}")
    public ModelAndView Cache(@PathVariable("schoolYear") @NotBlank String schoolYear) {
        cachingService.addYear(schoolYear);
        return new ModelAndView("redirect:/opleidingen/"+schoolYear);
    }
    //adds two years to the cache for dev purposes
    @GetMapping("/cachelast")
    public ModelAndView CacheLast() {
        cachingService.addYear("2019-20");
        cachingService.addYearEng("2019-20");
        return new ModelAndView("redirect:/opleidingen/2018-19");
    }


    //All programs of a schoolyear template
    @GetMapping("/opleidingen/{schoolYear}")
    public ModelAndView getProgramsOfYear(@RequestParam(required = false, name = "lang", defaultValue = "nl") String language, @PathVariable("schoolYear") @NotBlank String schoolYear) throws IOException {
        ModelAndView modelAndView = new ModelAndView("programsOfYear");

        //Retrieves academic years from API in homeservice and passes them to template
        List<String> academiejaren = homeService.getAcademiejaren();
        modelAndView.addObject("academiejaren", academiejaren);
        if (!academiejaren.contains(schoolYear)) {
            throw new AcademiejarenNotFoundException();
        }

        //Retrieves data from caching for nl page
        //Retrieves programTypes, fieldOfStudies and specializations of programs and passes them to template for filters
        List<String> programTypes = new ArrayList<>();
        List<String> fieldOfStudies = new ArrayList<>();
        List<String> specializations = new ArrayList<>();
        if (language.equals("nl")) {
            List<Program> programs = programRepository.findAllBySchoolYear(schoolYear);
            Collections.sort(programs);

            for (Program program : programs) {
                if (!programTypes.contains(program.getProgramType()))
                    programTypes.add(program.getProgramType());
                if (!fieldOfStudies.contains(program.getFieldOfStudy()))
                    fieldOfStudies.add(program.getFieldOfStudy());
                for (Specialization specialization : program.getSpecializations()) {
                    if (!specializations.contains(specialization.getName().toLowerCase())) {
                        String s = specialization.getName().substring(0, 1).toUpperCase() + specialization.getName().substring(1).toLowerCase();
                        specializations.add(s);
                    }
                }
            }
            modelAndView.addObject("programs", programs);
            modelAndView.addObject("specializations", specializations);
            modelAndView.addObject("programTypes", programTypes);
            modelAndView.addObject("fieldOfStudies", fieldOfStudies);
        }
        //Retrieves data from cache for eng page
        //Retrieves programTypes, fieldOfStudies and specializations of programs and passes them to template for filters
        else {
            List<ProgramEng> programsEng = programEngRepository.findAllBySchoolYear(schoolYear);
            Collections.sort(programsEng);

            for (ProgramEng program : programsEng) {
                if (!programTypes.contains(program.getProgramType()))
                    programTypes.add(program.getProgramType());
                if (!fieldOfStudies.contains(program.getFieldOfStudy()))
                    fieldOfStudies.add(program.getFieldOfStudy());
                for (SpecializationEng specialization : program.getSpecializations()) {
                    if (!specializations.contains(specialization.getName().toLowerCase())) {
                        String s = specialization.getName().substring(0, 1).toUpperCase() + specialization.getName().substring(1).toLowerCase();
                        specializations.add(s);
                    }
                }
            }
            modelAndView.addObject("programs", programsEng);
            modelAndView.addObject("specializations", specializations);
            modelAndView.addObject("programTypes", programTypes);
            modelAndView.addObject("fieldOfStudies", fieldOfStudies);
        }

        //Check if list of all academic years contains academic year in URL: If not, throw 404 error exception
        if (!academiejaren.contains(schoolYear)) {
            throw new AcademiejarenNotFoundException();
        }

        return modelAndView;
    }

    //All trajects of a program template
    @GetMapping("/opleidingen/{schoolYear}/{programCode}")
    public ModelAndView getTrajectsOfProgram(@PathVariable("schoolYear") @NotBlank String schoolYear,
                                             @PathVariable("programCode") @NotBlank String programCode,
                                             @RequestParam(required = false, name = "lang", defaultValue = "nl") String language) throws IOException {
        ModelAndView modelAndView = new ModelAndView("trajects");

        //Retrieves data from caching for nl page
        if (language.equals("nl")) {
            List<Program> programs = programRepository.findAllBySchoolYear(schoolYear);
            Collections.sort(programs);
            Program program = programRepository.findAllBySchoolYearAndProgramCode(schoolYear, programCode);

            //Make List from Iterable of trajects to sort alphabetically
            Iterable<Traject> trajectsList = program.getTrajects();
            List<Traject> trajects = new ArrayList<>();
            for (Traject traject : trajectsList) {
                trajects.add(traject);
            }
            Collections.sort(trajects);

            //Passes all programs, the requested program and its trajects to template
            modelAndView.addObject("programs", programs);
            modelAndView.addObject("program", program);
            modelAndView.addObject("trajects", trajects);
        }
        //Retrieves data from cache for eng page
        else {
            List<ProgramEng> programsEng = programEngRepository.findAllBySchoolYear(schoolYear);
            Collections.sort(programsEng);
            ProgramEng programEng = programEngRepository.findAllBySchoolYearAndProgramCode(schoolYear, programCode);

            //Make List from Iterable of trajects to sort alphabetically
            Iterable<TrajectEng> trajectsListEng = programEng.getTrajects();
            List<TrajectEng> trajectsEng = new ArrayList<>();
            for (TrajectEng trajectEng : trajectsListEng) {
                trajectsEng.add(trajectEng);
            }
            Collections.sort(trajectsEng);

            //Passes all programs, the requested program and its trajects to template
            modelAndView.addObject("programs", programsEng);
            modelAndView.addObject("program", programEng);
            modelAndView.addObject("trajects", trajectsEng);
        }

        //Retrieving programs from cache and academic years from API in homeService
        List<String> academiejaren = homeService.getAcademiejaren();
        Iterable<Program> programs = programRepository.findAllBySchoolYear(schoolYear);
        Program program = programRepository.findAllBySchoolYearAndProgramCode(schoolYear, programCode);

        //Add all programcodes to a list of programcodes for errormapping
        List<String> listProgCode = new ArrayList<>();
        for (Program p : programs)
            listProgCode.add(p.getProgramCode());
        //Check if list of all academic years contains academic year in URL: If not, throw 404 error exception
        if (!academiejaren.contains(schoolYear))
            throw new AcademiejarenNotFoundException();
        //Check if list of all programcodes contains programcode in URL: If not, throw 404 error exception
        if (!listProgCode.contains(programCode))
            throw new ProgramCodeNotFoundException();

        return modelAndView;
    }

    //Template for a traject which displays its years
    @GetMapping("/opleidingen/{schoolYear}/{programCode}/{opltraject}")
    public ModelAndView getTraject(@PathVariable("schoolYear") @NotBlank String schoolYear,
                                   @PathVariable("programCode") @NotBlank String programCode,
                                   @PathVariable("opltraject") @NotNull @NumberFormat(pattern = "####") int opltraject,
                                   @RequestParam(required = false, name = "lang", defaultValue = "nl") String language) throws IOException {
        ModelAndView modelAndView = new ModelAndView("traject");

        //Retrieves data from caching for nl page
        if (language.equals("nl")) {
            Traject traject = trajectRepository.findBySchoolYearAndOpltraject(schoolYear, opltraject);
            modelAndView.addObject("traject", traject);
        }
        //Retrieves data from caching for eng page
        else {
            TrajectEng trajectEng = trajectEngRepository.findBySchoolYearAndOpltraject(schoolYear, opltraject);
            modelAndView.addObject("traject", trajectEng);
        }

        //Retrieve data from cache for error mapping
        List<String> academiejaren = homeService.getAcademiejaren();
        Iterable<Program> programs = programRepository.findAllBySchoolYear(schoolYear);
        List<String> listProgCode = new ArrayList<>();
        List<Integer> listOplTrajecten = new ArrayList<>();
        List<Traject> trajects = trajectRepository.findAllBySchoolYear(schoolYear);
        for (Traject traject : trajects) {
            listOplTrajecten.add(traject.getOpltraject());
        }

        //Add all programcodes, retrieved from the cache, to a list of programcodes used for errormapping
        for (Program program : programs)
            listProgCode.add(program.getProgramCode());
        //Check if list of all academic years contains academic year in URL: If not, throw 404 error exception
        if (!academiejaren.contains(schoolYear))
            throw new AcademiejarenNotFoundException();
        //Check if list of all programcodes contains programcode in URL: If not, throw 404 error exception
        if (!listProgCode.contains(programCode))
            throw new ProgramCodeNotFoundException();
        //Check if list of all trajects contains traject in URL: If not, throw 404 error exception
        if (!listOplTrajecten.contains(opltraject)) {
            for (int t : listOplTrajecten)
                System.out.println(t);
            throw new OpleidingTrajectNotFoundException();
        }

        return modelAndView;
    }

    //Template for one year of a traject which displays its courses and options
    @GetMapping("/opleidingen/{schoolYear}/{programCode}/{opltraject}/{deeltraject}")
    public ModelAndView getTrajectYear(@PathVariable("schoolYear") @NotBlank String schoolYear,
                                       @PathVariable("programCode") @NotBlank String programCode,
                                       @PathVariable("opltraject") @NotNull @NumberFormat(pattern = "####") int opltraject,
                                       @PathVariable("deeltraject") @NotNull @NumberFormat(pattern = "####") int deeltraject,
                                       @RequestParam(required = false, name = "lang", defaultValue = "nl") String language) throws IOException {
        ModelAndView modelAndView = new ModelAndView("trajectYear");

        //Retrieve programs from cache and convert to List for sorting alphabetically
        Iterable<Program> programList = programRepository.findAllBySchoolYear(schoolYear);
        List<Program> programs = new ArrayList<>();
        for (Program program : programList) {
            programs.add(program);
        }
        Collections.sort(programs);

        //Creating objects for error mapping
        List<String> academiejaren = homeService.getAcademiejaren();
        List<String> listProgCode = new ArrayList<>();
        List<Integer> listDeelTrajecten = new ArrayList<>();
        List<Integer> listOplTrajecten = new ArrayList<>();

        //Retrieves requested trajectYear from cache and passes it to template
        if (language.equals("nl")) {
            Year trajectYear = yearRepository.findBySchoolYearAndTraject(schoolYear, deeltraject);
            modelAndView.addObject("trajectYear", trajectYear);
        }
        //Retrieves requested english trajectYear from cache and passes it to template
        else {
            YearEng trajectYearEng = yearEngRepository.findBySchoolYearAndTraject(schoolYear, deeltraject);
            modelAndView.addObject("trajectYear", trajectYearEng);
        }
        //Add all trajectYears from cache to a list used for error mapping
        Iterable<Year> trajectYears = yearRepository.findAllBySchoolYear(schoolYear);
        for (Year trajectYear : trajectYears)
            listDeelTrajecten.add(trajectYear.getTraject());
        //Add all traject from cache to a list used for error mapping
        Iterable<Traject> trajects = trajectRepository.findAllBySchoolYear(schoolYear);
        for (Traject traject : trajects)
            listOplTrajecten.add(traject.getOpltraject());
        //Add all programcodes to a list used for error mapping
        for (Program program : programs)
            listProgCode.add(program.getProgramCode());
        //Check if list of all academic years contains academic year in URL: If not, throw 404 error exception
        if (!academiejaren.contains(schoolYear))
            throw new AcademiejarenNotFoundException();
        //Check if list of all programcodes contains programcode in URL: If not, throw 404 error exception
        if (!listProgCode.contains(programCode))
            throw new ProgramCodeNotFoundException();
        //Check if list of all traject contains traject in URL: If not, throw 404 error exception
        if (!listOplTrajecten.contains(opltraject))
            throw new OpleidingTrajectNotFoundException();
        //Check if list of all traject contains academic year in URL: If not, throw 404 error exception
        if (!listDeelTrajecten.contains(deeltraject))
            throw new DeelTrajectNotFoundException();

        return modelAndView;
    }
}
