package com.newsagency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.newsagency.communication.Server;
import com.newsagency.data.repository.ArticleRepository;
import com.newsagency.presentation.controller.HomeController;
import com.newsagency.presentation.view.HomeView;

@SpringBootApplication
public class NewsagencyApplication implements CommandLineRunner {
	@Autowired
	HomeView homeView;
	@Autowired
	HomeController homeController;
	@Autowired
	Server server;

	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");
		SpringApplication.run(NewsagencyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		homeView.getFrame().setVisible(true);
		homeController.setHomeView(homeView);
		homeController.setArticleService(server.getArticleService());
		server.listenOnPort();

	}
}
