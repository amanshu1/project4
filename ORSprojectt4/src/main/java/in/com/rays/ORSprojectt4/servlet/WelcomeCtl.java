package in.com.rays.ORSprojectt4.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.com.rays.ORSprojectt4.utility.ServletUtility;

@WebServlet(name="WelcomeCtl",urlPatterns={"/WelcomeCtl"})
public class WelcomeCtl extends BaseCtl{
	
	private static final long serialVersionUID = 1L;

    //private static Logger log = Logger.getLogger(WelcomeCtl.class);

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
       // log.debug("WelcomeCtl Method doGet Started");

        ServletUtility.forward(ORSView.WELCOME_VIEW, request, response);

        //.debug("WelcomeCtl Method doGet Ended");
    }
    
	@Override
	protected String getView() {
				return ORSView.WELCOME_VIEW;
	}

}
