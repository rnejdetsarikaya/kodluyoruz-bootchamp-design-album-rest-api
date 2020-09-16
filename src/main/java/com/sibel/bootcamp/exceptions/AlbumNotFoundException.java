package com.sibel.bootcamp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.management.RuntimeErrorException;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Album Not Found")
public class AlbumNotFoundException extends Exception {

}
