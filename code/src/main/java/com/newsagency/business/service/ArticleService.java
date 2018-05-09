package com.newsagency.business.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.newsagency.data.entity.Article;
import com.newsagency.data.entity.Writer;
import com.newsagency.data.repository.ArticleRepository;
import com.newsagency.data.repository.WriterRepository;
import com.newsagency.presentation.controller.Observer;

@Service
public class ArticleService implements Subject {
	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	WriterRepository writerRepository;

	private ArrayList<Observer> observerList = new ArrayList<Observer>();

	public String findByTitle(String title) throws JsonProcessingException {
		Article article = articleRepository.findByArticleTitle(title);
		return JsonUtil.convertJavaToJson(article);
	}

	public String findAll() throws JsonProcessingException {
		List<Article> articlesList = articleRepository.findAll();
		return JsonUtil.convertListToJson(articlesList);
	}

	public void publishArticle(String articleContent) {
		String[] splited = articleContent.split("\\s+");
		String articleTitle = splited[0];
		String articleAbstract = splited[1];
		String articleBody = splited[2];
		String writerId = splited[3];
		Article article = new Article();
		article.setArticleTitle(articleTitle);
		article.setArticleAbstract(articleAbstract);
		article.setArticleBody(articleBody);
		Writer writer = writerRepository.findByWriterId(Integer.parseInt(writerId));
		article.setWriter(writer);
		articleRepository.save(article);
		notifyObservers();
	}

	public ArrayList<Observer> getObserverList() {
		return observerList;
	}

	public void setObserverList(ArrayList<Observer> observerList) {
		this.observerList = observerList;
	}

	@Override
	public void registerObserver(Observer o) {
		observerList.add(o);

	}

	@Override
	public void unregisterObserver(Observer o) {
		observerList.remove(observerList.indexOf(o));

	}

	@Override
	public void notifyObservers() {
		for (Iterator<Observer> it = observerList.iterator(); it.hasNext();) {
			Observer o = it.next();
			o.update();
		}

	}

}
