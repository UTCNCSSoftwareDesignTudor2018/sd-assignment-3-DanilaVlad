package com.newsagency.presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.newsagency.business.service.ArticleService;
import com.newsagency.business.service.JsonUtil;
import com.newsagency.communication.Client;
import com.newsagency.data.entity.Article;
import com.newsagency.data.entity.Writer;
import com.newsagency.presentation.view.WriterView;

@Controller
public class WriterController {
	@Autowired
	WriterView writerView;
	private Client client;
	private Writer writer;
	private int id;
	private String password;

	public WriterController(WriterView writerView) {
		this.writerView = writerView;
		writerView.setWriterUpdateProfileListener(new WriterUpdateProfileListener());
		writerView.setWriterDeleteProfileListener(new WriterDeleteProfileListener());
		writerView.setPublishListener(new PublishListener());
		writerView.chooseRelatedArticlesListener(new RelatedArticlesListener());
	}

	public boolean runClient(WriterController writerController, String usernameAndPassword) throws JSONException {
		client = new Client(writerController);
		try {
			client.run();
			writerController.validateLogin(usernameAndPassword);
			if (writer != null) {
				client.messageFromGUI("GET_WRITERS_ARTICLES", "");
				return true;
			}
			return false;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	public void displayArticleTitles(String articles)
			throws JSONException, JsonParseException, JsonMappingException, IOException {
		List<Article> articleList = JsonUtil.convertJsonToList(articles);
		for (Article a : articleList) {
			writerView.relatedModel.addElement(a.getArticleTitle());
		}
	}

	public void validateLogin(String jsonWriter)
			throws JSONException, JsonParseException, JsonMappingException, IOException {
		client.messageFromGUI("LOGIN_WRITER", jsonWriter);
	}

	public void validateWriter(String jsonWriter) {
		writer = JsonUtil.convertJsonToJava(jsonWriter, Writer.class);
		id = writer.getWriterId();
		password = writer.getWriterPassword();
	}

	public void createProfile(WriterController writerController, String jsonProfile) throws JSONException {
		client = new Client(writerController);
		try {
			client.run();
			System.out.println(jsonProfile);
			client.messageFromGUI("CREATE_PROFILE", jsonProfile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private class WriterUpdateProfileListener implements ActionListener {
		String passwordNew = "";

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == writerView.updateWriterBtn) {
				passwordNew = writerView.passwordNewText.getText();
				String details = passwordNew + " " + id;
				try {
					client.messageFromGUI("UPDATE_PROFILE", details);
				} catch (JSONException | IOException e1) {
					e1.printStackTrace();
				}
			}

		}
	}

	private class WriterDeleteProfileListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == writerView.deleteWriterBtn) {
				try {
					client.messageFromGUI("DELETE_PROFILE", id + "");
				} catch (JSONException | IOException e1) {

					e1.printStackTrace();
				}
			}
		}
	}

	private class PublishListener implements ActionListener {
		String articleTitle = "";
		String articleAbstract = "";
		String articleBody = "";

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == writerView.publishBtn) {
				ArticleService articleService = new ArticleService();
				try {
					articleTitle = writerView.areaTitle.getText();
					articleAbstract = writerView.areaAbstract.getText();
					articleBody = writerView.areaBody.getText();
					String articleContent = articleTitle + " " + articleAbstract + " " + articleBody + " " + id;
					client.messageFromGUI("PUBLISH_ARTICLE", articleContent);
				} catch (JSONException | IOException e1) {
					e1.printStackTrace();
				}
			}

		}
	}

	private class RelatedArticlesListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == writerView.chooseRelatedArticlesBtn) {
				JOptionPane.showMessageDialog(null,
						"Chosen related articles: " + writerView.relatedList.getSelectedValuesList(),
						"Related articles chosen", JOptionPane.INFORMATION_MESSAGE);

			}
		}
	}
}
