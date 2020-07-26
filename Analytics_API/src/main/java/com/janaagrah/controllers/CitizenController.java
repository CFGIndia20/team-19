package com.janaagrah.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.janaagrah.response.BaseResponseDTO;
import com.janaagrah.services.CitizenService;

/**
 * REST Controller that deals with displaying top citizens
 * based on given criteria
 * 
 * @author jimil
 *
 */
@RestController
public class CitizenController {
	
	@Autowired
	private CitizenService citizenService;

	/**
	 * GET API to return the top voted citizens. These are users that
	 * have reported the most issues and have been the most active on 
	 * the platform.
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/get/citizen", method = RequestMethod.GET)
	public BaseResponseDTO getTopVotedCitizens() throws Exception {
		BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
		try {
			baseResponseDTO.setData(citizenService.getAllCitizens());
		} catch(Exception e) {
			e.printStackTrace();
			baseResponseDTO.setError(e.getMessage());
		}
		return baseResponseDTO;
	}
	
	/**
	 * GET API that returns the top voted citizens for a concerned
	 * state. These are people that have been influential in development
	 * of the state.
	 * 
	 * @param state
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/get/citizen/{state}", method = RequestMethod.GET)
	public BaseResponseDTO getTopCitizensByState(@PathVariable(value="state") String state) throws Exception {
		BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
		try {
			if(state == null || state.isEmpty()) {
				throw new Exception("The state field cannot be null");
			}
			System.out.println("Path Variable, State: " + state);
			ArrayList<Object> response = citizenService.getTopCitizensByState(state.toLowerCase());
			if(response.size() == 0) {
				throw new Exception("There seems to be some error. We'll get back to you soon!");
			}
			baseResponseDTO.setData(response);
		} catch(Exception e) {
			e.printStackTrace();
			baseResponseDTO.setError(e.getMessage());
		}
		return baseResponseDTO;
	}
	
}
