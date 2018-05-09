package com.newsagency.business.service;

import com.newsagency.presentation.controller.Observer;

public interface Subject {
	public void registerObserver(Observer o);

	public void unregisterObserver(Observer o);

	public void notifyObservers();
}
