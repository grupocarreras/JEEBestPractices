package j2eebp.servlet;

import java.io.IOException;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import j2eebp.bl.UsuBL;
import j2eebp.security.J2eebpCallbackHandler;


@WebServlet("/AuthServlet")
public class AuthServlet extends javax.servlet.http.HttpServlet  {
	
	private static final long serialVersionUID = 1L;
	
//	@EJB
//	private j2eebp.bl.UsuBL usuBL;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthServlet() {
        super();
        // TODO Auto-generated constructor stub
    }



	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		//System.setProperty("java.security.auth.login.config", getServletContext().getRealPath("/WEB-INF/jaas.config"));
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	protected void doGet(javax.servlet.http.HttpServletRequest request, 
			javax.servlet.http.HttpServletResponse response) throws 
			ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.setProperty("java.security.auth.login.config", getServletContext().getRealPath("/WEB-INF/jaas.config"));

        
        String usucod = request.getParameter("usucod");
        String usupwd = request.getParameter("usupwd");
        boolean autorizado = false;
        //autorizado = usuBL.autentificar(usucod, usupwd);
        
        try {
                LoginContext context = new LoginContext("J2eebpLogin", new J2eebpCallbackHandler(usucod, usupwd));
                context.login();
                Subject subject = context.getSubject();
                autorizado = true;
                //context.logout();
                
        } catch (LoginException e) {
                e.printStackTrace();
        }               
        
        response.getWriter().println("ACCESS " + (autorizado?"SUCCEED":"DENIED"));

	}

}
