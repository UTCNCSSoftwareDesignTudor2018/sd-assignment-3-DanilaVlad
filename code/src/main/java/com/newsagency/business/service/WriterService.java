package com.newsagency.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newsagency.data.repository.WriterRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.newsagency.data.entity.Writer;

@Service
public class WriterService {
	@Autowired
	WriterRepository writerRepository;

	public String getWriterByUsernameAndPassword(String usernameAndPassword) throws JsonProcessingException {
		String[] splited = usernameAndPassword.split("\\s+");
		String username = splited[0];
		String password = splited[1];
		Writer writer = writerRepository.findByWriterUsernameAndWriterPassword(username, password);
		return JsonUtil.convertJavaToJson(writer);

	}

	public void saveWriter(String nameAndUsernameAndPassword) {
		String[] splited = nameAndUsernameAndPassword.split("\\s+");
		String name = splited[0];
		String username = splited[1];
		String password = splited[2];
		Writer writer = new Writer();
		writer.setWriterName(name);
		writer.setWriterUsername(username);
		writer.setWriterPassword(password);
		writerRepository.save(writer);
	}

	public void updateWriter(String username) {
		String[] splited = username.split("\\s+");
		String password = splited[0];
		String id = splited[1];
		Writer writer = writerRepository.findByWriterId(Integer.parseInt(id));
		writer.setWriterPassword(password);
		writerRepository.save(writer);
	}

	public void deleteWriter(String id) {
		Writer writer = writerRepository.findByWriterId(Integer.parseInt(id));
		writerRepository.delete(writer);
	}

}
