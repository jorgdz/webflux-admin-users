package com.github.com.jorgdz.app.documents;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.com.jorgdz.app.models.Country;
import com.github.com.jorgdz.app.models.Gender;

@JsonInclude(content = Include.NON_NULL)
@Document(collection = "users")
public class User {

	@Id
	private String id;
	
	private String name;
	
	private String surname;
	
	@DateTimeFormat(iso=ISO.DATE)
	private LocalDate birthDate;
	
	private Gender gender; 
	
	private String email;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String pass;
	
	private Boolean enabled;
	
	private Country country;
	
	@DBRef(lazy = true)
	private List<Role> roles;
	
	public User(String name, String surname, LocalDate birthDate, Gender gender, String email, String pass,
			Boolean enabled, Country country, List<Role> roles) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.gender = gender;
		this.email = email;
		this.pass = pass;
		this.enabled = enabled;
		this.country = country;
		
		this.roles = roles;
	}
	
	public User(String name, String surname, LocalDate birthDate, Gender gender, String email, String pass,
			Boolean enabled, Country country) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.gender = gender;
		this.email = email;
		this.pass = pass;
		this.enabled = enabled;
		this.country = country;
	}
	
	public User() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", birthDate=" + birthDate + ", gender="
				+ gender + ", email=" + email + ", pass=" + pass + ", enabled=" + enabled + ", country=" + country
				+ "]";
	}
	
}
