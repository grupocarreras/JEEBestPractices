package j2eebp.security;

import java.io.*;
import java.util.*;
import javax.security.auth.*;
import javax.security.auth.callback.*;
import javax.security.auth.login.*;
import javax.security.auth.spi.*;

//import j2eebp.bl.UsuBL;

/**
 * WedgeLoginModule autentifica usuarios leyendo nombres, contraseñas y roles de
 * base de datos
 * 
 * Using JAAS authentication from your application typically involves the
 * following steps:
 * 
 * - Create a LoginContext 
 * - Optionally pass a CallbackHandler to the LoginContext, for gathering or 
 *   processing authentication data 
 * - Perform authentication by calling the LoginContext's login() method 
 * - Perform privileged actions using the returned Subject (assuming login succeeds)
 * 
 * @version
 * @author u00281
 */
public class J2eebpLoginModule implements LoginModule {

	private Subject subject;
	private CallbackHandler callbackHandler;
	//private Map<String, ?> options;
	
	private boolean succeeded = false;	
	
	// @EJB(name = "j2eebp/bl/UsuBL")
	// private UsuBL usuBL;
	
	public J2eebpLoginModule() {
		System.out.println("Login Module - constructor called");
	}

	public boolean abort() throws LoginException {
		System.out.println("Login Module - abort called");
		return false;
	}

	public boolean commit() throws LoginException {
		System.out.println("Login Module - commit called");
		return succeeded;
	}
	
	public boolean logout() throws LoginException {	
		subject.getPrincipals().clear();
		System.out.println("Login Module - logout called");
		return false;
	}	
	
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
			Map<String, ?> options) {

		System.out.println("Login Module - initialize called");
		this.subject = subject;
		this.callbackHandler = callbackHandler;
		//this.options = options;

		System.out.println("testOption value: " + (String) options.get("testOption"));

		succeeded = false;
	}	
	
	

	public boolean login() throws LoginException {
		if (callbackHandler == null)
			throw new LoginException("no handler");

		NameCallback nameCall = new NameCallback("username: ");
		PasswordCallback passCall = new PasswordCallback("password: ", false);
		try {
			callbackHandler.handle(new Callback[] { nameCall, passCall });
		} catch (UnsupportedCallbackException e) {
			LoginException e2 = new LoginException("Unsupported callback");
			e2.initCause(e);
			throw e2;
		} catch (IOException e) {
			LoginException e2 = new LoginException("I/O exception in callback");
			e2.initCause(e);
			throw e2;
		}
		
		return checkLogin(nameCall.getName(), passCall.getPassword());

	}

	/**
	 * Comprueba si la informacion de autentificacion es correcta. Si lo es, el
	 * subject adquiere principals para el nombre de usuario y el rol
	 * 
	 * @param username Usuario
	 * @param password Contraseña
	 * @return true Si el usuario se autentifica correctamente
	 */
	private boolean checkLogin(String username, char[] password)
			throws LoginException {

		// Autentificacion		
		// succeed = usuBL.autentificar(username, password);


		if ("u00281".equals(username) && "123123".equals(password)) {
			System.out.println("Success! You get to log in!");
		    succeeded = true;
		    
			J2eebpPrincipal principalUserName = new J2eebpPrincipal("username", username);
		    if (!subject.getPrincipals().contains(principalUserName)) {
				subject.getPrincipals().add(principalUserName);	
		    }
			J2eebpPrincipal principalRole = new J2eebpPrincipal("role", "XXX");
		    if (!subject.getPrincipals().contains(principalRole)) {
				subject.getPrincipals().add(principalUserName);	
		    } 
		    
		} else {
			System.out.println("Failure! You don't get to log in");
			succeeded = false;
			throw new FailedLoginException("Sorry! No login for you.");
		}		

		return succeeded;

	}





}
