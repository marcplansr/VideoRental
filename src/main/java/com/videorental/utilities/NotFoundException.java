package com.videorental.utilities;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "El parámetro introducido no existe.")
public class NotFoundException extends Exception {

	// Generated UID
	private static final long serialVersionUID = -6007804884094753126L;

	public NotFoundException(String msg) {
		super(msg);
	}

	public NotFoundException() {
		super();
	}

}
