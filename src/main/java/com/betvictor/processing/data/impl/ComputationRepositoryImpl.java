package com.betvictor.processing.data.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.betvictor.processing.data.IComputationRepository;
import com.betvictor.processing.model.ProcessResponse;

@Repository
public class ComputationRepositoryImpl implements IComputationRepository {

	@Autowired
	private MongoOperations mongoTemplate;
	
	@Value("${db.collection.name}")
	private String collectionName;
	
	
	/*
	 * This method is for storing documents in MongoDB
	 * expects:
	 * 	response - ProcessResponse object as argument
	 * returns:
	 * 	if storing was succes it returns the document else it returns null
	 * */
	public ProcessResponse store(ProcessResponse response) {
		
		Optional<ProcessResponse> pr = Optional.ofNullable(mongoTemplate.save(response, collectionName));

		return pr.isPresent() ? pr.get() : null;
	}
	
	
	/*This method is extracting ten last documents by sorting them
	 *in descendence by id
	 *returns:
	 *	the last ten records
	 * */
	public List<ProcessResponse> getLastTen() {					
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.DESC, "_id"));
		query.limit(10);
		
		List<ProcessResponse> prl = mongoTemplate.find(query, ProcessResponse.class, collectionName);
		
		return prl;
	}
}
