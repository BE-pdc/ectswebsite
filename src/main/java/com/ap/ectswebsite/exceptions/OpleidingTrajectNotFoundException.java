package com.ap.ectswebsite.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Dit opleidingtraject bestaat niet.")
public class OpleidingTrajectNotFoundException extends RuntimeException {
}
