package com.db.tf3.tradeDemo.exception;

public class ValidationFailedException extends RuntimeException {

	private static final long serialVersionUID = -2131346436098154993L;

	private final String id;

	public ValidationFailedException(final String id) {
		super("Invalid Id: " + id);
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
