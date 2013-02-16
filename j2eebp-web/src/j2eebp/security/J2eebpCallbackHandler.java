package j2eebp.security;

import javax.security.auth.callback.*;

/**
 * Este manejador presenta el usuario y contraseña obtenidos de la pantalla de
 * login.
 * 
 * @version
 * @author u00281
 * 
 */
public class J2eebpCallbackHandler implements CallbackHandler {

	private String username;
	private String password;

	public J2eebpCallbackHandler(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public void handle(Callback[] callbacks) {
		for (Callback callback : callbacks) {
			if (callback instanceof NameCallback) {
				((NameCallback) callback).setName(username);
			} else if (callback instanceof PasswordCallback) {
				((PasswordCallback) callback).setPassword(password.toCharArray());
			}
		}
	}

}
