package com.newsagency.presentation.view;

import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import org.springframework.stereotype.Component;

@Component
public class WriterView {
	public JFrame writerFrame;
	public JPanel writerPanel;

	public JTextField passwordNewText;

	public JButton updateWriterBtn;
	public JButton deleteWriterBtn;
	public JButton chooseRelatedArticlesBtn;
	public JButton publishBtn;

	public JTextArea areaTitle;
	public JTextArea areaAbstract;
	public JTextArea areaBody;
	public JScrollPane listScrollPane;

	public JList<String> relatedList;
	public DefaultListModel<String> relatedModel;

	HomeView homeView = new HomeView();

	public WriterView() {
		initialize();
	}

	public void setWriterUpdateProfileListener(ActionListener actionListener) {
		updateWriterBtn.addActionListener(actionListener);
	}

	public void setWriterDeleteProfileListener(ActionListener actionListener) {
		deleteWriterBtn.addActionListener(actionListener);
	}

	public void setchooseRelatedArticlesBtnListener(ActionListener actionListener) {
		chooseRelatedArticlesBtn.addActionListener(actionListener);
	}

	public void setPublishListener(ActionListener actionListener) {
		publishBtn.addActionListener(actionListener);
	}

	public void chooseRelatedArticlesListener(ActionListener actionListener) {
		chooseRelatedArticlesBtn.addActionListener(actionListener);
	}

	private void initialize() {
		writerFrame = new JFrame();
		writerFrame.setBounds(0, 0, 800, 550);
		writerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		writerFrame.getContentPane().setLayout(null);
		writerFrame.setTitle("Writer");
		writerFrame.setLocationRelativeTo(null);

		writerPanel = new JPanel();
		writerPanel.setBounds(0, 0, 800, 550);
		writerPanel.setLayout(null);
		writerFrame.add(writerPanel);

		passwordNewText = new JTextField("New password");
		passwordNewText.setBounds(10, 10, 120, 20);
		writerPanel.add(passwordNewText);

		updateWriterBtn = new JButton("Update profile");
		updateWriterBtn.setBounds(270, 10, 120, 20);
		writerPanel.add(updateWriterBtn);

		deleteWriterBtn = new JButton("Delete profile");
		deleteWriterBtn.setBounds(400, 10, 120, 20);
		writerPanel.add(deleteWriterBtn);

		chooseRelatedArticlesBtn = new JButton("Confirm related Articles");
		chooseRelatedArticlesBtn.setBounds(580, 340, 190, 20);
		writerPanel.add(chooseRelatedArticlesBtn);

		publishBtn = new JButton("Publish Article");
		publishBtn.setBounds(580, 370, 190, 30);
		writerPanel.add(publishBtn);

		areaTitle = new JTextArea("Title");
		areaTitle.setBounds(10, 40, 760, 20);
		writerPanel.add(areaTitle);

		areaAbstract = new JTextArea("Abstract");
		areaAbstract.setBounds(10, 70, 760, 50);
		writerPanel.add(areaAbstract);

		areaBody = new JTextArea("Body");
		areaBody.setBounds(10, 130, 760, 200);
		writerPanel.add(areaBody);

		relatedModel = new DefaultListModel<>();
		relatedList = new JList<>(relatedModel);
		relatedList.setBounds(10, 340, 560, 150);
		relatedList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		relatedList.setLayoutOrientation(JList.VERTICAL);
		writerPanel.add(relatedList);

		listScrollPane = new JScrollPane(relatedList);
		listScrollPane.setBounds(10, 340, 560, 150);
		listScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		writerPanel.add(listScrollPane);

	}

}
