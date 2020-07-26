package com.janaagrah.infra.objects;

import org.springframework.stereotype.Component;

/**
 * POJO to model individual complains in the Firestore
 * database.
 * 
 * @author jimil
 *
 */
public class Complain {

	private String city;
	private String complain_category;
	private String complain_text;
	private String district;
	private String state;
	private String tweet_id;
	private String username;
	
	Complain(String city, String complain_category, String complain_text, String district,
			String state, String tweet_id, String username) {
		this.city = city;
		this.complain_category = complain_category;
		this.complain_text = complain_text;
		this.district = district;
		this.state = state;
		this.tweet_id = tweet_id;
		this.username = username;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getComplain_category() {
		return complain_category;
	}

	public void setComplain_category(String complain_category) {
		this.complain_category = complain_category;
	}

	public String getComplain_text() {
		return complain_text;
	}

	public void setComplain_text(String complain_text) {
		this.complain_text = complain_text;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTweet_id() {
		return tweet_id;
	}

	public void setTweet_id(String tweet_id) {
		this.tweet_id = tweet_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
