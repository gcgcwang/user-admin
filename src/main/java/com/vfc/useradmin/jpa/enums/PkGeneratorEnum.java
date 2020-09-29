package com.vfc.useradmin.jpa.enums;

public enum PkGeneratorEnum {

	SEQUENCE,
	AUTO_INCREASING,
	SELECTED_INCREASING,
	CURE_UUID;
	
	private PkGeneratorEnum() {}
}
