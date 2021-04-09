package com.betvictor.processing.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betvictor.processing.data.IComputationRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class RepositoryController {
	
	@Autowired
	private IComputationRepository computationRepository;
	
	@ApiOperation(value = "Retrieving last ten documents", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved last ten documents"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@GetMapping(value = "/betvictor/history", produces = "application/json")
	public ResponseEntity<?> getLastTen(){
		return new ResponseEntity<>(computationRepository.getLastTen(),OK);
	}

}
