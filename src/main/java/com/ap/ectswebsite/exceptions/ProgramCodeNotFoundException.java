package com.ap.ectswebsite.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "This program code doesn't exist.")
public class ProgramCodeNotFoundException extends RuntimeException {

}
