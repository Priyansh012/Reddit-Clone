package com.example.spring.reddit.clone.exceptions;

public class SpringRedditException extends RuntimeException {

	public SpringRedditException(String exMessage) {
		super(exMessage);
	}

	public SpringRedditException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }
}
