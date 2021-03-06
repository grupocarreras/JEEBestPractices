package j2eebp.security;

import java.security.*;

/**
 * Principals represent Subject identities. They implement the
 * java.security.Principal interface (which predates JAAS) and
 * java.io.Serializable. A Subject's most important method is getName(), which
 * returns an identity's string name. Since a Subject instance contains an array
 * of Principals, it can thus have multiple names.
 * 
 * Because a social security number, login ID, email address, and so on, can all
 * represent one user, multiple identities prove common in the real world.
 * 
 * @version 1.0 2004-09-14
 * @author Cay Horstmann
 */

public class J2eebpPrincipal implements Principal {

	private String descr;
	private String value;

	/**
	 * Construye un Principal para guardar un valor y una descripcion
	 * 
	 * @param roleName
	 *            the role name
	 */
	public J2eebpPrincipal(String descr, String value) {
		this.descr = descr;
		this.value = value;
	}

	/**
	 * Devuelve el rol del principal
	 * 
	 * @return the role name
	 */
	public String getName() {
		return descr + "=" + value;
	}

	public boolean equals(Object otherObject) {
		if (this == otherObject)
			return true;
		if (otherObject == null)
			return false;
		if (getClass() != otherObject.getClass())
			return false;
		J2eebpPrincipal other = (J2eebpPrincipal) otherObject;
		return getName().equals(other.getName());
	}

	public int hashCode() {
		return getName().hashCode();
	}

}
