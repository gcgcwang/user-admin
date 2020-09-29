package com.vfc.useradmin.core.exception;

public class LocalException extends Exception {
		//异常信息
		public String message;
		
		public LocalException(String message){
			super(message);
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
}
