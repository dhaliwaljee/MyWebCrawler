package org.kd.singh.tags;

public class Anchor {
	private String anchorLabel;
	private String anchorHref;
	public Anchor() {
		// TODO Auto-generated constructor stub
	}
	
	public Anchor(String label, String href) {
		this.anchorLabel = label;
		this.anchorHref = href;
	}
	
	/**
	 * @return the anchorLabel
	 */
	public String getAnchorLabel() {
		return anchorLabel;
	}
	/**
	 * @param anchorLabel the anchorLabel to set
	 */
	public void setAnchorLabel(String anchorLabel) {
		this.anchorLabel = anchorLabel;
	}
	/**
	 * @return the anchorHref
	 */
	public String getAnchorHref() {
		return anchorHref;
	}
	/**
	 * @param anchorHref the anchorHref to set
	 */
	public void setAnchorHref(String anchorHref) {
		this.anchorHref = anchorHref;
	}
	
}
