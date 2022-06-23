package in.com.rays.ORSprojectt4.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.com.rays.ORSprojectt4.bean.BaseBean;
import in.com.rays.ORSprojectt4.bean.StudentBean;
import in.com.rays.ORSprojectt4.exception.ApplicationException;
import in.com.rays.ORSprojectt4.exception.DuplicateRecordException;
import in.com.rays.ORSprojectt4.model.CollegeModel;
import in.com.rays.ORSprojectt4.model.StudentModel;
import in.com.rays.ORSprojectt4.utility.DataUtility;
import in.com.rays.ORSprojectt4.utility.DataValidator;
import in.com.rays.ORSprojectt4.utility.PropertyReader;
import in.com.rays.ORSprojectt4.utility.ServletUtility;

@WebServlet("/ctl/StudentCtl")
public class StudentCtl extends BaseCtl {

	// private static Logger log = Logger.getLogger(StudentCtl.class);

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		protected void preload(HttpServletRequest request) {

			System.out.println("Studentctl preload");
			CollegeModel model = new CollegeModel();
			try {
				List l = model.list();
				request.setAttribute("collegeList", l);
			} catch (ApplicationException e) {
				// log.error(e);
			}

		}

		@Override
		protected boolean validate(HttpServletRequest request) {

			// log.debug("StudentCtl Method validate Started");

			System.out.println("StudentCtl vlaidate");
			boolean pass = true;

//			String op = DataUtility.getString(request.getParameter("operation"));
		//	String email = request.getParameter("email");
			//String dob = request.getParameter("dob");

			if (DataValidator.isNull(request.getParameter("firstname"))) {
				request.setAttribute("firstname", PropertyReader.getValue("error.require", "Frist Name"));
				pass = false;
			} else if (!DataValidator.isName(request.getParameter("firstname"))) {
				request.setAttribute("firstname", PropertyReader.getValue("error.name", "Frist Name"));
				pass = false;
			}
			if (DataValidator.isNull(request.getParameter("lastname"))) {
				request.setAttribute("lastname", PropertyReader.getValue("error.require", "Last Name"));
				pass = false;
			} else if (!DataValidator.isName(request.getParameter("lastname"))) {
				request.setAttribute("lastname", PropertyReader.getValue("error.name", "Last Name"));
				pass = false;
			}
			if (DataValidator.isNull(request.getParameter("mobile"))) {
				request.setAttribute("mobile", PropertyReader.getValue("error.require", "Mobile No"));
				pass = false;
			} else if (!DataValidator.isMobileNo(request.getParameter("mobile"))) {
				request.setAttribute("mobile", 
	                    "Mobile No. must start from 6-9 and have 10 digits");
						pass = false;
			}
			if (DataValidator.isNull(request.getParameter("email"))) {
				request.setAttribute("email", PropertyReader.getValue("error.require", "Email Id"));
				pass = false;
			} else if (!DataValidator.isEmail(request.getParameter("email"))) {
				request.setAttribute("email", PropertyReader.getValue("error.email", "Email Id"));
				pass = false;
			}
			if (DataValidator.isNull(request.getParameter("collegename"))) {
				request.setAttribute("collegename", PropertyReader.getValue("error.require", "College Name"));
				pass = false;
			}
			if (DataValidator.isNull(request.getParameter("dob"))) {
				request.setAttribute("dob", PropertyReader.getValue("error.require", "Date Of Birth"));
				pass = false;
			} else if (!DataValidator.isDate(request.getParameter("dob"))) {
				request.setAttribute("dob", PropertyReader.getValue("error.date", "Date Of Birth"));
				pass = false;
			}

			// log.debug("StudentCtl Method validate Ended");

			return pass;
		}

		@Override
		protected BaseBean populateBean(HttpServletRequest request) {

			// log.debug("StudentCtl Method populatebean Started");

			System.out.println("Student ctl populate");
			StudentBean bean = new StudentBean();

			bean.setId(DataUtility.getInt(request.getParameter("id")));

			bean.setFirstName(DataUtility.getString(request.getParameter("firstname")));
			bean.setLastName(DataUtility.getString(request.getParameter("lastname")));
			bean.setDob(DataUtility.getDate(request.getParameter("dob")));
			bean.setMobileNo(DataUtility.getString(request.getParameter("mobile")));
			bean.setEmail(DataUtility.getString(request.getParameter("email")));
			bean.setCollegeId(DataUtility.getInt(request.getParameter("collegename")));

			populateDTO(bean, request);

			// log.debug("StudentCtl Method populatebean Ended");

			return bean;
		}

		/**
		 * Contains Display logics
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			// log.debug("StudentCtl Method doGet Started");

			String op = DataUtility.getString(request.getParameter("operation"));
			int id = DataUtility.getInt(request.getParameter("id"));

			// get model
			System.out.println("Student Ctl get");
			StudentModel model = new StudentModel();
			if (id > 0 || op != null) {
				StudentBean bean;
				try {
					bean = model.findByPK(id);
					ServletUtility.setBean(bean, request);
				} catch (ApplicationException e) {
					// log.error(e);
					ServletUtility.handleException(e, request, response);
					return;
				}
			}
			ServletUtility.forward(getView(), request, response);
			// log.debug("StudentCtl Method doGett Ended");
		}

		/**
		 * Contains Submit logics
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			// log.debug("StudentCtl Method doPost Started");
			System.out.println(" Student Ctl inside do post");
			String op = DataUtility.getString(request.getParameter("operation"));

			// get model

			StudentModel model = new StudentModel();

			int id = DataUtility.getInt(request.getParameter("id"));
//			System.out.println("inside save  op");
			if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
				System.out.println("inside save  op");

				StudentBean bean = (StudentBean) populateBean(request);
				System.out.println("inside populate bean");

				try {
					if (id > 0) {
						model.update(bean);
					
						ServletUtility.setSuccessMessage("Successfully Updated", request);

					} else {

						long pk = model.add(bean);
						System.out.println("STUDENT LSFK"+ pk);
						ServletUtility.setSuccessMessage("Successfully Saved", request);

					//	bean.setId(pk);
					}

					ServletUtility.setBean(bean, request);

				}catch (DuplicateRecordException e) {
	                ServletUtility.setBean(bean, request);
	                ServletUtility.setErrorMessage(
	                        "Student Email Id already exists", request);
	            } 
				catch (Exception e) {
					//e.printStackTrace();
					// log.error(e);
					// ServletUtility.handleException(e, request, response);
				//return;
				}
				 

			}

			 else if (OP_CANCEL.equalsIgnoreCase(op)) {

				ServletUtility.redirect(ORSView.STUDENT_LIST_CTL, request, response);
				return;

			}
			ServletUtility.forward(getView(), request, response);

			// log.debug("StudentCtl Method doPost Ended");
		}

		@Override
		protected String getView() {

			return ORSView.STUDENT_VIEW;
		}

	}
