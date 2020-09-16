package com.sibel.bootcamp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Resource already exists")
public class AlbumDuplicateException extends RuntimeException{

}
