package com.videorental.utilities;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "El parámetro introducido es incorrecto.")
public class InvalidParamException extends Exception {

	// Generated UID
	private static final long serialVersionUID = -6387762997715866289L;

	public InvalidParamException(String msg) {
		super(msg);
	}

	public InvalidParamException() {
		super();
	}
}
