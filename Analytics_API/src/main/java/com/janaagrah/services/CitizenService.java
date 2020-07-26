package com.janaagrah.services;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.janaagrah.repositories.CitizenRepository;

/**
 * Service class that handles data cleaning with respect to
 * the API requests pertaining to the citizen controller.
 * 
 * @author shahj
 *
 */
@Service
public class CitizenService {

	@Autowired
	private CitizenRepository citizenRepository;
	
	/**
	 * Returns the top voted citizens across the country.
	 * 
	 * @return
	 */
	public ArrayList<Object> getAllCitizens() throws Exception {
		return citizenRepository.getTopCitizens();
	}
	
	/**
	 * Returns the top citizens for a particular state. This includes
	 * people who have been actively reporting issues in the concerned
	 * state.
	 * 
	 * @param state
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Object> getTopCitizensByState(String state) throws Exception {
		if(state == null || state.isEmpty()) {
			throw new Exception("The value of the state field cannot be null");
		}
		return citizenRepository.getCitizensByState(state);
	}
}
