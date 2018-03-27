package junkie.utils.searchword.search;

public class SearchException extends Exception {
	private static final long serialVersionUID = 3186813369895150020L;

	public SearchException() {
		super();
	}

	public SearchException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public SearchException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public SearchException(String arg0) {
		super(arg0);
	}

	public SearchException(Throwable arg0) {
		super(arg0);
	}

}
