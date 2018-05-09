package com.newsagency.presentation.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.stereotype.Component;

@Component
public class HomeView {

	public JFrame frame;

	public JPanel panelHome;
	public JPanel loginPanel;
	public JPanel createProfilePanel;

	public JTextField writerUsernameText;
	public JTextField writerPasswordText;

	public JButton readerBtn;
	public JButton loginWriterBtn;
	public JButton createWriterBtn;

	public JTextField profileNameText;
	public JTextField profileUsernameText;
	public JTextField profilePasswordText;

	public HomeView() {
		initialize();
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setReaderListener(ActionListener actionListener) {
		readerBtn.addActionListener(actionListener);
	}

	public void setWriterLoginListener(ActionListener actionListener) {
		loginWriterBtn.addActionListener(actionListener);
	}

	public void setWriterCreateProfileListener(ActionListener actionListener) {
		createWriterBtn.addActionListener(actionListener);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 300, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Home");
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		panelHome = new JPanel();
		panelHome.setBounds(0, 0, 300, 150);
		panelHome.setBackground(Color.GRAY);
		panelHome.setLayout(null);
		frame.add(panelHome);

		readerBtn = new JButton("Reader");
		readerBtn.setBounds(75, 20, 130, 25);
		panelHome.add(readerBtn);

		loginWriterBtn = new JButton("Writer Login");
		loginWriterBtn.setBounds(75, 50, 130, 25);
		panelHome.add(loginWriterBtn);

		writerUsernameText = new JTextField();
		writerPasswordText = new JTextField();

		loginPanel = new JPanel(new GridLayout(0, 1));
		loginPanel.add(new JLabel("Username:"));
		loginPanel.add(writerUsernameText);
		loginPanel.add(new JLabel("Password:"));
		loginPanel.add(writerPasswordText);

		createWriterBtn = new JButton("Create profile");
		loginPanel.add(createWriterBtn);

		profileNameText = new JTextField();
		profileUsernameText = new JTextField();
		profilePasswordText = new JTextField();

		createProfilePanel = new JPanel(new GridLayout(0, 1));
		createProfilePanel.add(new JLabel("Name:"));
		createProfilePanel.add(profileNameText);
		createProfilePanel.add(new JLabel("Username:"));
		createProfilePanel.add(profileUsernameText);
		createProfilePanel.add(new JLabel("Password:"));
		createProfilePanel.add(profilePasswordText);

	}

	public int createLoginOptionPane() {
		return JOptionPane.showConfirmDialog(null, loginPanel, "Login", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
	}

	public int createProfileOptionPane() {
		return JOptionPane.showConfirmDialog(null, createProfilePanel, "Create profile", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
	}
}
