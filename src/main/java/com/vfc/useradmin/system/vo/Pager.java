package com.vfc.useradmin.system.vo;

import java.io.Serializable;
import java.util.List;

public class Pager<T>  implements Serializable{

	private int pageSize = 15;
	private  int pageNum = 1;
	
	private  List<T> rows;
	private  long total=0;
	
	private  long beginNum=0;
	private  long endNum=0;
	
	private  long beginPageNum=0;
	private  long endPageNum=0;
	private  int pageLength=5;
	private  int facPageLength=5;
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	
	public long getBeginNum() {
		return  (long)(this.pageSize*(this.pageNum-1)+1);
	}
	public long getEndNum() {
		return  (long)(this.pageSize*this.pageNum);
	}
	
	public long getBeginPageNum() {
		this.checkandSetSpecialExp();
		return this.beginPageNum;
	}
	public long getEndPageNum() {
		this.checkandSetSpecialExp();
		return this.endPageNum;
		
	}
	
	public int getPageLength() {
		if(this.pageSize==0){
			return 1;
		}
		int  pageLen=(int)Math.ceil(this.total/(double)this.pageSize);
		this.facPageLength=pageLen;
		if(pageLen > 5){
			return 5;
		}
		return pageLen;
	}
	public int getFacPageLength() {
		return this.facPageLength;
	}
	private boolean checkandSetSpecialExp(){
		int pagelen= this.getPageLength();
		long midCut=(long) Math.floor(pagelen/2L);
		if((this.pageNum-midCut) <= 0){
			this.beginPageNum = 1;
			this.endPageNum = this.pageNum+pagelen-1;
			return false;
		}
		if((this.pageNum+midCut) > pagelen){
			this.endPageNum = this.facPageLength;
			this.beginPageNum = this.facPageLength-pagelen+1;
			return false;
		}
		this.beginPageNum = this.pageNum-midCut;
		this.endPageNum = this.pageNum+midCut;
		return true;
	}
}
