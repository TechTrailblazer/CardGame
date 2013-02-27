package ben.kn.cardgames;

/**
 * Enum represents colors available in this project.
 * 
 * @author Ben (bknear@gmail.com)
 * @since Feb 26, 2013
 */
public enum Color {
	RED("FF0000"), BLACK("000000");

	private String hex;

	Color(String hex) {
		this.hex = hex;
	}

	public String getHex() {
		return hex;
	}
}