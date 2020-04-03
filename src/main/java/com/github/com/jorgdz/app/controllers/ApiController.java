package com.github.com.jorgdz.app.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class ApiController {
	
	protected SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {	
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
}
