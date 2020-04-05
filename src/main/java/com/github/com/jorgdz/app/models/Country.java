package com.github.com.jorgdz.app.models;

import java.io.Serializable;

public class Country implements Serializable {
	
	private static final long serialVersionUID = -8299734984435435863L;
	
	private String name;
	private Translation translations;
	
	public Country() {}
	
	public Country(String name) {
		this.name = name;
	}
	
	public Country(String name, Translation translations) {
		this.name = name;
		this.translations = translations;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Translation getTranslations() {
		return translations;
	}

	public void setTranslations(Translation translations) {
		this.translations = translations;
	}

	@Override
	public String toString() {
		return "Country [name=" + name + ", translations=" + translations + "]";
	}
	
}
