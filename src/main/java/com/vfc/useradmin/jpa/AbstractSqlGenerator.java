package com.vfc.useradmin.jpa;

public abstract class AbstractSqlGenerator implements SqlGenerator{

	protected EntryMapper entryMapper;
	
	public AbstractSqlGenerator(){}
	
	public AbstractSqlGenerator(EntryMapper entryMapper){
		this.entryMapper = entryMapper;
	}
	
	public EntryMapper getEntryMapper() {
		return entryMapper;
	}

	public void setEntryMapper(EntryMapper entryMapper) {
		this.entryMapper = entryMapper;
	}
	
}
