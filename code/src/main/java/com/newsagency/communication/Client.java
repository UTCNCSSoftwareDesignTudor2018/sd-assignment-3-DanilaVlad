package com.newsagency.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.JSONException;

import com.newsagency.presentation.controller.ReaderController;
import com.newsagency.presentation.controller.WriterController;

public class Client {
	BufferedReader in;
	PrintWriter out;
	ReaderController readerController;
	WriterController writerController;

	public Client(ReaderController readerController) {

		this.readerController = readerController;
	}

	public Client(WriterController writerController) {

		this.writerController = writerController;
	}

	public void run() throws IOException {
		Socket socket = new Socket("localhost", 9001);
		out = new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	public void messageFromGUI(String message, String argument) throws IOException, JSONException {
		while (true) {
			if (message.equals("GET_ALL_ARTICLES")) {
				out.println("GET_ALL_ARTICLES");
				String articles = in.readLine();
				readerController.displayArticleTitles(articles);
				break;
			}
			if (message.equals("READ_ARTICLE")) {
				out.println("READ_ARTICLE");
				out.println(argument);
				String article = in.readLine();
				readerController.displayArticleBody(article);
				break;
			}
			if (message.equals("LOGIN_WRITER")) {
				out.println("LOGIN_WRITER");
				out.println(argument);
				String writer = in.readLine();
				writerController.validateWriter(writer);
				break;
			}
			if (message.equals("CREATE_PROFILE")) {
				out.println("CREATE_PROFILE");
				out.println(argument);
				break;
			}
			if (message.equals("UPDATE_PROFILE")) {
				out.println("UPDATE_PROFILE");
				out.println(argument);
				break;
			}
			if (message.equals("DELETE_PROFILE")) {
				out.println("DELETE_PROFILE");
				out.println(argument);
				break;
			}
			if (message.equals("PUBLISH_ARTICLE")) {
				out.println("PUBLISH_ARTICLE");
				out.println(argument);
				break;
			}
			if (message.equals("GET_WRITERS_ARTICLES")) {
				out.println("GET_WRITERS_ARTICLES");
				String articles = in.readLine();
				writerController.displayArticleTitles(articles);
				break;
			}
		}
	}

}