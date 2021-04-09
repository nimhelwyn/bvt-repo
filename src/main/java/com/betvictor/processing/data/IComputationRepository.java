package com.betvictor.processing.data;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.betvictor.processing.model.ProcessResponse;

@Repository
public interface IComputationRepository {
	
	ProcessResponse store(ProcessResponse response);
	
	public List<ProcessResponse> getLastTen();
}
