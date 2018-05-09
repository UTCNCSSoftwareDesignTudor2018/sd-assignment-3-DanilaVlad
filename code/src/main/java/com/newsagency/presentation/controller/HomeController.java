package com.newsagency.presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.newsagency.business.service.ArticleService;
import com.newsagency.presentation.view.HomeView;
import com.newsagency.presentation.view.ReaderView;
import com.newsagency.presentation.view.WriterView;

@Controller
public class HomeController {
	@Autowired
	HomeView homeView;
	@Autowired
	ArticleService articleService;
	@Autowired
	ReaderController readerController;
	@Autowired
	WriterController writerController;

	public HomeController(HomeView homeView) {
		this.homeView = homeView;
		homeView.setReaderListener(new ReaderListener());
		homeView.setWriterLoginListener(new WriterLoginListener());
		homeView.setWriterCreateProfileListener(new WriterCreateProfileListener());
	}

	public ArticleService getArticleService() {
		return articleService;
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	public void setHomeView(HomeView homeView) {
		this.homeView = homeView;
	}

	private class ReaderListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == homeView.readerBtn) {
				ReaderView readerView = new ReaderView();
				readerView.readerFrame.setVisible(true);
				ReaderController readerController = new ReaderController(readerView);
				try {
					readerController.runClient(readerController, articleService);
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
			}

		}
	}

	private class WriterLoginListener implements ActionListener {
		String username = "";
		String password = "";

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == homeView.loginWriterBtn) {
				WriterView writerView = new WriterView();
				int optionPaneResult = homeView.createLoginOptionPane();
				if (optionPaneResult == JOptionPane.OK_OPTION) {
					username = homeView.writerUsernameText.getText();
					password = homeView.writerPasswordText.getText();
					String usernameAndPassword = username + " " + password;
					WriterController writerController = new WriterController(writerView);
					try {
						if (writerController.runClient(writerController, usernameAndPassword)) {
							writerView.writerFrame.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(homeView.panelHome, "Invalid username or password", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (JSONException e1) {
						JOptionPane.showMessageDialog(homeView.panelHome, "Invalid username or password", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					System.out.println("Cancelled");
				}
			}

		}

	}

	private class WriterCreateProfileListener implements ActionListener {
		String name = "";
		String username = "";
		String password = "";

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == homeView.createWriterBtn) {
				WriterView writerView = new WriterView();
				int optionPaneResult = homeView.createProfileOptionPane();
				if (optionPaneResult == JOptionPane.OK_OPTION) {
					name = homeView.profileNameText.getText();
					username = homeView.profileUsernameText.getText();
					password = homeView.profilePasswordText.getText();
					String nameAndUsernameAndPassword = name + " " + username + " " + password;
					WriterController writerController = new WriterController(writerView);
					try {
						writerController.createProfile(writerController, nameAndUsernameAndPassword);
					} catch (JSONException e1) {
						e1.printStackTrace();
					}
				} else {
					System.out.println("Cancelled");
				}
			}

		}
	}

}
