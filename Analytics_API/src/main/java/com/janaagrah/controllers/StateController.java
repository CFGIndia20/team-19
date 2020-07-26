package com.janaagrah.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.janaagrah.response.BaseResponseDTO;
import com.janaagrah.services.StateService;

/**
 * REST Controller that deals with displaying top states
 * based on the result.
 * 
 * @author jimil
 *
 */
@RestController
public class StateController {

	@Autowired
	private StateService stateService;
	
	/**
	 * GET API to return top voted states. These are states that have
	 * the best response time to issues.
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/get/states", method=RequestMethod.GET)
	public BaseResponseDTO getTopStates() throws Exception {
		BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
		try {
			baseResponseDTO.setData(stateService.getAllStates());
		} catch(Exception e) {
			e.printStackTrace();
			baseResponseDTO.setError(e.getMessage());
		}
		return baseResponseDTO;
	}
	
	/**
	 * API to return the state-wise stats to the dashboard. This includes
	 * number of issues raised in the past month, number of issues resolved
	 * and the overall state rank, based on the quality of citizenship.
	 * 
	 * @param state
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/api/get/stats/{state}", method = RequestMethod.GET)
	public BaseResponseDTO getStateStats(@PathVariable(value = "state") String state) throws Exception {
		BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
		try {
			if(state == null || state.isEmpty()) {
				throw new Exception("The value of the state field cannot be null.");
			}
			baseResponseDTO.setData(stateService.getStateWiseStats(state.toLowerCase()));
		} catch(Exception e) {
			e.printStackTrace();
			baseResponseDTO.setError(e.getMessage());
		}
		return baseResponseDTO;
	}
}
