package com.web.testing.example.factory;

public interface AbstractFactory<T> {
	public T create(String browserType);
}
