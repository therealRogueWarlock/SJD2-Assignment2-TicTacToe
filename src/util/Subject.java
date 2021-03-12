package util;

import java.beans.PropertyChangeListener;

public interface Subject {
	void addListener(String propertyName, PropertyChangeListener listener);

	void removeListener(String propertyName, PropertyChangeListener listener);
}

