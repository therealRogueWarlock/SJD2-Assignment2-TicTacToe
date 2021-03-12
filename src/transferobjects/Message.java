package transferobjects;

import java.io.Serializable;

public class Message implements Serializable {
	private String name;
	private String message;
	private String targetName;

	public Message(String msg) {
		this.name = null;
		message = msg;
		targetName = "Lobby";
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTarget(String target) {
		targetName = target;
	}

	public String getName() {
		return name;
	}

	public String getStringMessage() {
		return message;
	}

	public String getTarget() {
		return targetName;
	}

}
