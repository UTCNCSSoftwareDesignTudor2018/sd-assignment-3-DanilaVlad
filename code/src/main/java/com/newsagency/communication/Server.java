package com.newsagency.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.newsagency.business.service.ArticleService;
import com.newsagency.business.service.WriterService;

@Component
public class Server {
	@Autowired
	ArticleService articleService;
	@Autowired
	WriterService writerService;
	private static final int PORT = 9001;

	public void listenOnPort() throws Exception {
		System.out.println("The server is running on port " + PORT);
		ServerSocket listener = new ServerSocket(PORT);
		try {
			while (true) {
				new Handler(listener.accept(), articleService, writerService).start();
			}
		} finally {
			listener.close();
		}
	}

	public ArticleService getArticleService() {
		return articleService;
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	private static class Handler extends Thread {
		private String message;
		private Socket socket;
		private BufferedReader in;
		private PrintWriter out;
		private ArticleService articleService;
		private WriterService writerService;

		public Handler(Socket socket, ArticleService articleService, WriterService writerService) {
			this.socket = socket;
			this.articleService = articleService;
			this.writerService = writerService;
		}

		public void run() {
			try {
				out = new PrintWriter(socket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				while (true) {
					message = in.readLine();
					if (message == null) {
						return;
					}
					if (message.equals("GET_ALL_ARTICLES")) {
						out.println(articleService.findAll());
					}
					if (message.equals("READ_ARTICLE")) {
						String title = in.readLine();
						out.println(articleService.findByTitle(title));
					}
					if (message.equals("LOGIN_WRITER")) {
						String usernameAndPassword = in.readLine();
						out.println(writerService.getWriterByUsernameAndPassword(usernameAndPassword));
					}
					if (message.equals("CREATE_PROFILE")) {
						String nameAndUsernameAndPassword = in.readLine();
						writerService.saveWriter(nameAndUsernameAndPassword);
					}
					if (message.equals("UPDATE_PROFILE")) {
						String details = in.readLine();
						writerService.updateWriter(details);
					}
					if (message.equals("DELETE_PROFILE")) {
						String id = in.readLine();
						writerService.deleteWriter(id);
					}
					if (message.equals("PUBLISH_ARTICLE")) {
						String articleContent = in.readLine();
						articleService.publishArticle(articleContent);
					}
					if (message.equals("GET_WRITERS_ARTICLES")) {
						out.println(articleService.findAll());
					}
				}

			} catch (IOException e) {
				System.out.println(e);
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
				}
			}
		}
	}

}
