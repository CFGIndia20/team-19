package com.janaagrah.services;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.janaagrah.repositories.StateRepository;

/**
 * Service class that pertains to handling HTTP requests
 * pertaining to finding states.
 * 
 * @author shahj
 *
 */
@Service
public class StateService {

	@Autowired
	private StateRepository stateRepository;
	
	/**
	 * Returns the top states across the country based on their
	 * quality of citizenship.
	 * 
	 * @return
	 */
	public ArrayList<Object> getAllStates() throws Exception {
		return stateRepository.getTopStates();
	}
	
	/**
	 * Service method to fetch state-wise documents and display
	 * aggregation statistics over the same.
	 * 
	 * @param state
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Object> getStateWiseStats(String state) throws Exception {
		if(state == null || state.isEmpty()) {
			throw new Exception("The value of the state field cannot be empty.");
		}
		return stateRepository.getStatsForState(state);
	}
	
}
