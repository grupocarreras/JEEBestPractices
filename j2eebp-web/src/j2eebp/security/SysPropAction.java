package j2eebp.security;

import java.security.PrivilegedAction;

public class SysPropAction implements PrivilegedAction<String> {
	/**
	 * Constructs an action for looking up a given property.
	 * 
	 * @param propertyName the property name (such as "user.home")
	 */
	public SysPropAction(String propertyName) {
		this.propertyName = propertyName;
	}

	public String run() {
		return System.getProperty(propertyName);
	}

	private String propertyName;
}
