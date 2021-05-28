package md.pentlaog.goodreadsclone.exceptions;

public class BookNotFoundException extends RuntimeException {
	public BookNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}

	public BookNotFoundException(String msg) {
		super(msg);
	}
	public BookNotFoundException(Long id) {
		super("Book with id " + id + " doesn't exist");

	}
}
