package com.newsagency.presentation.controller;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.newsagency.business.service.ArticleService;
import com.newsagency.business.service.JsonUtil;
import com.newsagency.communication.Client;
import com.newsagency.data.entity.Article;
import com.newsagency.presentation.view.ReaderView;

@Controller
public class ReaderController implements Observer {
	@Autowired
	ReaderView readerView;
	private String title;
	private Client client;

	public ReaderController(ReaderView readerView) {
		this.readerView = readerView;
		readerView.setReaderTableListener(new ReaderTableListener());

	}

	public void runClient(ReaderController readerController, ArticleService articleService) throws JSONException {
		client = new Client(readerController);
		try {
			client.run();
			articleService.getObserverList().add(readerController);
			client.messageFromGUI("GET_ALL_ARTICLES", "");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void setReaderView(ReaderView readerView) {
		this.readerView = readerView;
	}

	public void displayArticleTitles(String articles)
			throws JSONException, JsonParseException, JsonMappingException, IOException {
		readerView.tableModel.getDataVector().removeAllElements();
		readerView.tableModel.fireTableDataChanged();
		List<Article> articleList = JsonUtil.convertJsonToList(articles);
		for (Article a : articleList) {
			readerView.tableModel.addRow(new Object[] { a.getArticleTitle() });
		}
	}

	public void displayArticleBody(String jsonArticle)
			throws JSONException, JsonParseException, JsonMappingException, IOException {

		Article article = JsonUtil.convertJsonToJava(jsonArticle, Article.class);
		readerView.areaInfo.setText("");
		readerView.areaInfo
				.append("Abstract: \n" + article.getArticleAbstract() + "\n\n" + article.getArticleBody() + "\n");
	}

	private class ReaderTableListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == readerView.readerTable) {

				Point point = e.getPoint();
				int row = readerView.readerTable.rowAtPoint(point);
				int col = readerView.readerTable.columnAtPoint(point);
				String title = (String) readerView.readerTable.getValueAt(row, col);
				if (e.getClickCount() == 1 && readerView.readerTable.getSelectedRow() != -1) {
					try {
						client.messageFromGUI("READ_ARTICLE", title);
					} catch (IOException | JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}

		}
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public void update() {
		try {
			client.messageFromGUI("GET_ALL_ARTICLES", "");
		} catch (IOException | JSONException e) {
			e.printStackTrace();
		}
	}

}
