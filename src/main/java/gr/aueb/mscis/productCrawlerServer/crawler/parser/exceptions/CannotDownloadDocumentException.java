package gr.aueb.mscis.productCrawlerServer.crawler.parser.exceptions;

public class CannotDownloadDocumentException extends Exception{

	private static final long serialVersionUID = 1349054347879676575L;

	public CannotDownloadDocumentException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CannotDownloadDocumentException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public CannotDownloadDocumentException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CannotDownloadDocumentException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CannotDownloadDocumentException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
