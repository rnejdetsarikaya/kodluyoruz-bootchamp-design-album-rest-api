package com.sibel.bootcamp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Delete Operation Failed")
public class AlbumDeleteException extends RuntimeException {
}
