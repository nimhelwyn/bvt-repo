package com.betvictor.processing.kafka.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.betvictor.processing.data.IComputationRepository;
import com.betvictor.processing.model.ProcessResponse;

@Service
public class Consumer {

	@Autowired
	private IComputationRepository computationRepository;
	
	@KafkaListener(topics = "words.processed")
	public void receive(ProcessResponse payload) {
		computationRepository.store(payload);
	}
}
