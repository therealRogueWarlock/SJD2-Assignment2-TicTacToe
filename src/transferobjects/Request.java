package transferobjects;

import java.io.Serializable;

public class Request implements Serializable {
	private String type;
	private Object arg;
	private Object arg2;

	public Request(String type, Object arg) {
		this.type = type;
		this.arg = arg;
	}

	public String getType() {
		return type;
	}

	public Object getArg() {
		return arg;
	}

	public Object getArg2() {
		return arg2;
	}

	public void setArg2(Object arg2) {
		this.arg2 = arg2;
	}

}
