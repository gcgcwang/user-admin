package com.vfc.useradmin.jpa.enums;

public enum JoinTypeEnum {

	LEFT_JOIN("LEFT JOIN","左连接"),
	INNER_JOIN("INNER JOIN","内连接");
	
	private String code;
	private String text;
	
	private JoinTypeEnum(String code, String text) {
		this.code=code;
		this.text=text;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public static String getText(String code) {
		if (code == null || "".equals(code))
			return null;
		for (JoinTypeEnum c : JoinTypeEnum.values()) {
			if (code.equals(c.getCode())) {
				return c.getText();
			}
		}
		return null;
	} 
	 
	public static String getJson() {
		StringBuffer sb=new StringBuffer();
		sb.append("[");
		for (JoinTypeEnum c : JoinTypeEnum.values()) {
			sb.append("{\"code\":\"").append(c.getCode()).append("\",\"text\":\"").append(c.getText()).append("\"},");
		}
		sb.append("]");
		String str=sb.toString();
		return str.replace(",]", "]");
	}
}
