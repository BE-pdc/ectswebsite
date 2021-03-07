package com.ap.ectswebsite.controllers;

import com.ap.ectswebsite.entities.Program;
import com.ap.ectswebsite.repositories.ProgramRepository;
import com.ap.ectswebsite.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@Controller
public class ErrorHandlerController implements ErrorController {

    @Autowired
    ProgramRepository programRepository;

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        ModelAndView mav404 = new ModelAndView("error/404");
        ModelAndView mav500 = new ModelAndView("error/500");
        ModelAndView mavDef = new ModelAndView("error");

        Iterable<Program> programs = programRepository.findAll();

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                mav404.addObject("programs", programs);
                mav404.addObject("schoolYear", "2018-19");
                mav500.addObject("language", "nl");
                return mav404;
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
                mav500.addObject("programs", programs);
                mav500.addObject("schoolYear", "2018-19");
                mav500.addObject("language", "nl");
                return mav500;
            }
        }
        mavDef.addObject("programs", programs);
        mav500.addObject("schoolYear", "2018-19");
        mav500.addObject("language", "nl");
        return mavDef;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
