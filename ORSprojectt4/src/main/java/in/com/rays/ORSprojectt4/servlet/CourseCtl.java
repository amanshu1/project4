package in.com.rays.ORSprojectt4.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.com.rays.ORSprojectt4.bean.BaseBean;
import in.com.rays.ORSprojectt4.bean.CourseBean;
import in.com.rays.ORSprojectt4.exception.ApplicationException;
import in.com.rays.ORSprojectt4.exception.DuplicateRecordException;
import in.com.rays.ORSprojectt4.model.CourseModel;
import in.com.rays.ORSprojectt4.utility.DataUtility;
import in.com.rays.ORSprojectt4.utility.DataValidator;
import in.com.rays.ORSprojectt4.utility.PropertyReader;
import in.com.rays.ORSprojectt4.utility.ServletUtility;

@WebServlet(name="CourseCtl",urlPatterns={"/ctl/CourseCtl"})
public class CourseCtl extends BaseCtl{
	//private static Logger log = Logger.getLogger(CourseCtl.class);

	protected boolean validate(HttpServletRequest request) {
		//log.debug("CourseCtl validate started");
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Course Name"));
			 pass = false ;
		}else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.name", "Course Name"));
			 pass = false ;
		}
		if (DataValidator.isNull(request.getParameter("duration"))) {
			request.setAttribute("duration", PropertyReader.getValue("error.require", "Duration"));
			pass = false ;
		}
		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			pass = false ;
		}else if (!DataValidator.isName(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.name", "Description"));
			pass = false ;
		}

		//log.debug("CourseCtl validate End");
		return pass;
	}

	protected BaseBean populateBean(HttpServletRequest request){
		//log.debug("CourseCtl PopulatedBean started");
		CourseBean bean = new CourseBean();
		
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setCourseName(DataUtility.getString(request.getParameter("name")));
		System.out.println("popppp    "+request.getParameter("duration"));
		bean.setDuration(DataUtility.getString(request.getParameter("duration")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
	
		populateDTO(bean, request);
		//log.debug("CourseCtl PopulatedBean End");
		return bean;
	}
	
    /**
     * Contains Display logics
     */

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//log.debug("Do get method od courseCtl started");
		
		// get Model
		CourseModel model = new CourseModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		
		if(id>0){
			CourseBean bean;
			try{
			bean = model.findByPK(id);
			ServletUtility.setBean(bean, request);
			
			}catch(ApplicationException e){
				//log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
	}
    /**
     * Contains Submit logics
     */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//log.debug("Do Post method of CourseCtl started ");
		String op = DataUtility.getString(request.getParameter("operation"));
		
		// Get Model
		CourseModel model = new CourseModel();
		long id = DataUtility.getLong(request.getParameter("id"));
	
		if(OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)){
			CourseBean bean =(CourseBean) populateBean(request);
		try{
			if(id>0){		
					model.update(bean);
					ServletUtility.setSuccessMessage("Successfully Updated", request);

			}else{
				 //long pk
				  model.add(bean);
					ServletUtility.setSuccessMessage("Successfully Saved", request);

			//		bean.setId(PK);
			}
				ServletUtility.setBean(bean, request);
		
		}catch(ApplicationException e ){
			e.printStackTrace();
			//log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		} catch (DuplicateRecordException e) {
			ServletUtility.setBean(bean, request);
			ServletUtility.setErrorMessage("Course Name Already Exist", request);
			
		}		
		}
		else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request, response);
			return;
		}
		else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.COURSE_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);
		//log.debug("Do Post method CourseCtl Ended");
	
	}

	@Override
	protected String getView() {
		return ORSView.COURSE_VIEW;
	}

}
