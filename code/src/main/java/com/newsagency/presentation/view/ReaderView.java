package com.newsagency.presentation.view;

import java.awt.Color;
import java.awt.event.MouseAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

@Component
public class ReaderView {

	public JFrame readerFrame;
	public JPanel readerPanel;
	public JScrollPane readerScroll;
	public JScrollPane areaScrollPane;
	public DefaultTableModel tableModel;
	public JTextArea areaInfo;

	public JTable readerTable;
	HomeView homeView = new HomeView();

	public ReaderView() {
		initialize();
	}

	public void setReaderTableListener(MouseAdapter mouseAdapter) {
		readerTable.addMouseListener(mouseAdapter);
	}

	private void initialize() {
		readerFrame = new JFrame();
		readerFrame.setBounds(0, 0, 850, 450);
		readerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		readerFrame.setLocationRelativeTo(null);
		readerFrame.getContentPane().setLayout(null);
		readerFrame.setTitle("Reader");

		readerPanel = new JPanel();
		readerPanel.setLayout(null);
		readerPanel.setBounds(0, 0, 850, 450);
		readerPanel.setVisible(true);
		readerPanel.setBackground(Color.GRAY);

		Object col[] = { "Title" };
		tableModel = new DefaultTableModel(col, 0);
		readerTable = new JTable();
		readerTable.setModel(tableModel);
		readerTable.setBounds(10, 10, 300, 385);
		readerPanel.add(readerTable);

		readerScroll = new JScrollPane(readerTable);
		readerScroll.setBounds(10, 10, 300, 385);
		readerScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		readerFrame.add(readerScroll);
		readerFrame.add(readerPanel);

		areaInfo = new JTextArea();
		areaInfo.setBounds(330, 10, 490, 385);

		areaScrollPane = new JScrollPane(areaInfo);
		areaScrollPane.setBounds(330, 10, 490, 385);
		areaScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		readerPanel.add(areaScrollPane);

	}
}
