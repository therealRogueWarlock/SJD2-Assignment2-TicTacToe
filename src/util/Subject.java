package util;


public interface Subject {

	void addListener(String propertyName, int listener);

	void removeListener(String propertyName, int listener);

}
