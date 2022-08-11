package com.usrmarcos.desafioSenior.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author m-ant
 *
 */

public class UtilController {

	public Logger getLogger(Class<?> pClass) {
		return LoggerFactory.getLogger(pClass);
	}
	
	public String getExceptionMessage(Exception e) {
		Throwable cause = e.getCause();
		String message = cause != null ? cause.getMessage() : "";
		message = message == "" ? e.getMessage() : message;
		
		return message;
	}
	
}
