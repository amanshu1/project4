package in.com.rays.ORSprojectt4.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import in.com.rays.ORSprojectt4.bean.BaseBean;
import in.com.rays.ORSprojectt4.bean.RoleBean;
import in.com.rays.ORSprojectt4.bean.UserBean;
import in.com.rays.ORSprojectt4.exception.ApplicationException;
import in.com.rays.ORSprojectt4.model.RoleModel;
import in.com.rays.ORSprojectt4.model.UserModel;
import in.com.rays.ORSprojectt4.utility.DataUtility;
import in.com.rays.ORSprojectt4.utility.DataValidator;
import in.com.rays.ORSprojectt4.utility.PropertyReader;
import in.com.rays.ORSprojectt4.utility.ServletUtility;

@WebServlet(name="LoginCtl",urlPatterns={"/LoginCtl"})
public class LoginCtl extends BaseCtl{
	 private static final long serialVersionUID = 1L;
	    public static final String OP_REGISTER = "Register";
	    public static final String OP_SIGN_IN = "Sign In";
	    public static final String OP_SIGN_UP = "Sign Up";
	    public static final String OP_LOG_OUT = "logout";

	    //private static Logger log = Logger.getLogger(LoginCtl.class);

	    @Override
	    protected boolean validate(HttpServletRequest request) {

	        //log.debug("LoginCtl Method validate Started");

	        boolean pass = true;


	       String op = request.getParameter("operation");
	        
	        if (OP_SIGN_UP.equalsIgnoreCase(op) || OP_LOG_OUT.equals(op)) {
	            return pass;
	        }

	        String login = request.getParameter("login");

	        if (DataValidator.isNull(login)) {
	            request.setAttribute("login",
	                    PropertyReader.getValue("error.require", "Login Id"));
	            pass = false;
	        } else if (!DataValidator.isEmail(login)) {
	            request.setAttribute("login",
	                    PropertyReader.getValue("error.email", "Login "));
	            pass = false;
	        }
	        if (DataValidator.isNull(request.getParameter("password"))) {
	            request.setAttribute("password",
	                    PropertyReader.getValue("error.require", "Password"));
	            pass = false;
	        }else if (!DataValidator.isPassword(request.getParameter("password"))) {
				request.setAttribute("password", "Password must have aplhanumeric characters");
				pass = false;
			}

	       // log.debug("LoginCtl Method validate Ended");

	        return pass;
	    }

	    @Override
	    protected BaseBean populateBean(HttpServletRequest request) {

	      //  log.debug("LoginCtl Method populatebean Started");

	        UserBean bean = new UserBean();

	        bean.setId(DataUtility.getLong(request.getParameter("id")));
	        bean.setLogin(DataUtility.getString(request.getParameter("login")));
	        bean.setPassword(DataUtility.getString(request.getParameter("password")));

	       // log.debug("LoginCtl Method populatebean Ended");

	        return bean;
	    }

	    /**
	     * Display Login form
	     *
	     */
	    protected void doGet(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	        HttpSession session = request.getSession(true);
	       // log.debug(" Method doGet Started");

	        String op = DataUtility.getString(request.getParameter("operation"));

			if (OP_LOG_OUT.equals(op)) {

				session = request.getSession();

				session.invalidate();

				ServletUtility.setSuccessMessage("You Have Logged Out Succesfully", request);

				ServletUtility.forward(getView(), request, response);

				return ;

			}

			ServletUtility.forward(getView(), request, response);

			// log.debug("UserCtl Method doPost Ended");

		}

	    /**
	     * Submitting or login action performing
	     *
	     */
	    protected void doPost(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	        HttpSession session = request.getSession(true);
	       // log.debug(" Method doPost Started");

	        String op = DataUtility.getString(request.getParameter("operation"));

	        // get model
	        UserModel model = new UserModel();
	        RoleModel role = new RoleModel();
	        String uri = request.getParameter("URI");
	        long id = DataUtility.getLong(request.getParameter("id"));

	        if (OP_SIGN_IN.equalsIgnoreCase(op)) {

	            UserBean bean = (UserBean) populateBean(request);

	            try {

	                bean = model.authenticate(bean.getLogin(), bean.getPassword());

	                if (bean != null) {
	                    session.setAttribute("user", bean);
	                    long rollId = bean.getRoleId();

	                    RoleBean rolebean = role.findByPK(rollId);

	                    if (rolebean != null) {
	                        session.setAttribute("role", rolebean.getName());
	                    }
	                 // front-controller

						System.out.println("URI---- " + uri);
						if (uri == null || "null".equalsIgnoreCase(uri)) {
							ServletUtility.forward(ORSView.WELCOME_VIEW, request, response);
							return;

						} else {

							ServletUtility.redirect(uri, request, response);
							return;

						}

						/*
						 * ServletUtility.forward(ORSView.WELCOME_VIEW, request, response); return;
						 */

	                } else {
	                    bean = (UserBean) populateBean(request);
	                    ServletUtility.setBean(bean, request);
	                    ServletUtility.setErrorMessage(
	                            "Invalid LoginId And Password", request);
	                }

	            } catch (ApplicationException e) {
	                //log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            }catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	        } else if (OP_SIGN_UP.equalsIgnoreCase(op)) {

	            ServletUtility.redirect(ORSView.USER_REGISTRATION_CTL, request,
	                    response);
	            return;

	        }

	        ServletUtility.forward(getView(), request, response);

	       // log.debug("UserCtl Method doPost Ended");
	    }
	@Override
	protected String getView() {
		return ORSView.LOGIN_VIEW;
	}
	

}
