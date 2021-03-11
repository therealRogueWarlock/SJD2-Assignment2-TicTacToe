package transferobjects;

public class Message {
	private String name;
	private String message;
	private String targetName;

	public Message(String msg) {
		this.name = null;
		message = msg;
		targetName = "Lobby";
	}

	public String getName() {
		return name;
	}

	public void setName() {
		this.name = name;
	}

	public void setTarget(String target) {
		targetName = target;
	}

	public String getStringMessage() {
		return message;
	}

	public String getTarget() {
		return targetName;
	}

}
