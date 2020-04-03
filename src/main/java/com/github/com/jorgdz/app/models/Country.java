package com.github.com.jorgdz.app.models;

import java.io.Serializable;

public class Country implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8299734984435435863L;
	
	private String name;
	
	public Country(String name) {
		this.name = name;
	}
	
	public Country(Country country) {
		this.name = country.getName();
	}
	
	public Country() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Country [name=" + name + "]";
	}
	
}
