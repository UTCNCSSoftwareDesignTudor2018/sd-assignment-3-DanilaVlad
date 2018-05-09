package com.newsagency.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newsagency.data.entity.Writer;

@Repository
public interface WriterRepository extends JpaRepository<Writer, Integer> {

	public Writer findByWriterUsernameAndWriterPassword(String username, String password);

	public Writer findByWriterUsername(String username);

	public Writer findByWriterId(int writerId);
}
