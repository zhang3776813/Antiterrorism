package com.whfp.anti_terrorism.bean;


/**
 * 所有接受bean的基类
 * @author coky
 *
 */
public class BasicBean<T> {
	protected T data;
	protected int result;
	protected String msg;
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "BasicBean [data=" + data + ", result=" + result + ", msg="
				+ msg + "]";
	}
	
}
