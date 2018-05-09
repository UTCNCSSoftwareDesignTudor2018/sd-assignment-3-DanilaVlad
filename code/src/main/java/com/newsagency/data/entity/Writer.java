package com.newsagency.data.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "writers")
public class Writer {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int writerId;
	@Column
	private String writerName;
	@Column
	private String writerUsername;
	@Column
	private String writerPassword;

	@OneToMany(mappedBy = "writer", fetch = FetchType.EAGER)
	private List<Article> articles;

	public List<Article> getArticles() {
		return articles;
	}

	public int getWriterId() {
		return writerId;
	}

	public void setWriterId(int writerId) {
		this.writerId = writerId;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public String getWriterUsername() {
		return writerUsername;
	}

	public void setWriterUsername(String writerUsername) {
		this.writerUsername = writerUsername;
	}

	public String getWriterPassword() {
		return writerPassword;
	}

	public void setWriterPassword(String writerPassword) {
		this.writerPassword = writerPassword;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return "Writer [writerId=" + writerId + ", writerName=" + writerName + ", writerUsername=" + writerUsername
				+ ", writerPassword=" + writerPassword + ", articles=" + articles + "]";
	}

}
