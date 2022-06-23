package in.com.rays.ORSprojectt4.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.com.rays.ORSprojectt4.bean.BaseBean;
import in.com.rays.ORSprojectt4.bean.CollegeBean;
import in.com.rays.ORSprojectt4.bean.CourseBean;
import in.com.rays.ORSprojectt4.bean.FacultyBean;
import in.com.rays.ORSprojectt4.bean.SubjectBean;
import in.com.rays.ORSprojectt4.exception.ApplicationException;
import in.com.rays.ORSprojectt4.exception.DuplicateRecordException;
import in.com.rays.ORSprojectt4.model.CollegeModel;
import in.com.rays.ORSprojectt4.model.CourseModel;
import in.com.rays.ORSprojectt4.model.FacultyModel;
import in.com.rays.ORSprojectt4.model.SubjectModel;
import in.com.rays.ORSprojectt4.utility.DataUtility;
import in.com.rays.ORSprojectt4.utility.DataValidator;
import in.com.rays.ORSprojectt4.utility.PropertyReader;
import in.com.rays.ORSprojectt4.utility.ServletUtility;

@WebServlet ("/ctl/FacultyCtl")
public class FacultyCtl extends BaseCtl{

//private static Logger log = Logger.getLogger(FacultyCtl.class);
	
	protected void preload (HttpServletRequest request){
		
		System.out.println("Faculty ctl preload  in ");
		
		CourseModel crsm = new CourseModel();
		CollegeModel cm = new CollegeModel();
		SubjectModel stm = new SubjectModel();
		
		List<CourseBean> clist = new ArrayList<CourseBean>();
		List<CollegeBean> colist = new ArrayList<CollegeBean>();
		List<SubjectBean> slist = new ArrayList<SubjectBean>();
		
		try {
			clist = crsm.list();
			colist = cm.list();
			slist = stm.list();
			
				request.setAttribute("CourseList", clist);
				request.setAttribute("CollegeList", colist);
				request.setAttribute("SubjectList", slist);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	protected boolean validate(HttpServletRequest request){
		
		System.out.println("Faculty ctl validate  in ");
		
		//log.debug("Validate Method of Faculty Ctl Started");
		boolean pass = true;
		if(DataValidator.isNull(request.getParameter("firstname"))){
			request.setAttribute("firstname", PropertyReader.getValue("error.require", "First Name"));
			 pass = false;
		}else if (!DataValidator.isValidName(request.getParameter("firstname"))) {
			request.setAttribute("firstname", PropertyReader.getValue("error.name", "First Name"));
			 pass = false;
		}
		if(DataValidator.isNull(request.getParameter("lastname"))){
			request.setAttribute("lastname", PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		}else if (!DataValidator.isValidName(request.getParameter("lastname"))) {
			request.setAttribute("lastname", PropertyReader.getValue("error.name", "Last Name"));
			 pass = false;
			
		}
		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("doj"))) {
			request.setAttribute("doj", PropertyReader.getValue("error.require", "Date of Joining"));
			pass = false;
		} /*
			 * else if(!DataValidator.isDate(request.getParameter("doj"))){
			 * request.setAttribute("doj", PropertyReader.getValue("error.date",
			 * "Date of Joining")); pass=false; }
			 */
		if(DataValidator.isNull(request.getParameter("qualification"))){
			request.setAttribute("qualification", PropertyReader.getValue("error.require", "Qualification"));
			pass = false;
		}else if(!DataValidator.isName(request.getParameter("qualification"))){
			request.setAttribute("qualification", PropertyReader.getValue("error.name", "Qualification"));
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("loginid"))){
			request.setAttribute("loginid", PropertyReader.getValue("error.require", "LoginId"));
			pass = false;
		}else if (!DataValidator.isEmail(request.getParameter("loginid"))) {
			request.setAttribute("loginid", PropertyReader.getValue("error.email", "This"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobileno"))) {
			request.setAttribute("mobileno", PropertyReader.getValue("error.require", "MobileNo"));
			pass = false;
		}else if (!DataValidator.isMobileNo(request.getParameter("mobileno"))) {
			request.setAttribute("mobileno", "Mobile No. must be 10 Digit and No. Series start with 6-9");
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("collegeid"))) {
			request.setAttribute("collegeid", PropertyReader.getValue("error.require", "CollegeName"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("courseid"))) {
			request.setAttribute("courseid", PropertyReader.getValue("error.require", "CourseName"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("subjectid"))) {
			request.setAttribute("subjectid", PropertyReader.getValue("error.require", "SubjectName"));
			pass = false;
		}
		
		
		System.out.println("Faculty ctl validate out ");
		//log.debug("validate Ended");
		return pass;
	}
	
	protected BaseBean populateBean(HttpServletRequest request){
		//log.debug("populate bean faculty ctl started");
		System.out.println(" populate bean ctl  in ");
		FacultyBean fb = new FacultyBean();
	
		fb.setId(DataUtility.getLong(request.getParameter("id")));
		fb.setFirstName(DataUtility.getString(request.getParameter("firstname")));
		fb.setLastName(DataUtility.getString(request.getParameter("lastname")));
		fb.setGender(DataUtility.getString(request.getParameter("gender")));
		fb.setDateOfJoining(DataUtility.getDate(request.getParameter("doj")));
		
		fb.setQualification(DataUtility.getString(request.getParameter("qualification")));
		fb.setEmail(DataUtility.getString(request.getParameter("loginid")));
		fb.setMobileNo(DataUtility.getString(request.getParameter("mobileno")));
		fb.setCollegeId(DataUtility.getInt(request.getParameter("collegeid")));
		fb.setCourseId(DataUtility.getInt(request.getParameter("courseid")));
		fb.setSubjectId(DataUtility.getInt(request.getParameter("subjectid")));
//		fb.setCourseName(DataUtility.getString(request.getParameter("courseid")));
//		fb.setSubjectName(DataUtility.getString(request.getParameter("subjectid")));
		populateDTO(fb, request);
		//log.debug("populate fb faculty ctl Ended");
		System.out.println(" populate fb ctl out ");
		return fb;
	}
	
	
    /**
     * Contains Display logics
     */




	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	//	log.debug("Do get of faculty ctl Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		
		//Get Model
		FacultyModel fm = new FacultyModel();
		int id = (int) DataUtility.getLong(request.getParameter("id"));
		
		if(id>0 || op!= null){
			FacultyBean fb;
			try {
				fb = fm.findByPK(id)
;
			ServletUtility.setBean(fb, request);
			
			} catch (ApplicationException e) {
				e.printStackTrace();
				//log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		System.out.println(" do get out ");
		//log.debug("Do get of  faculty ctl Ended");
		ServletUtility.forward(getView(), request, response);
	}

    /**
     * Contains Submit logics
     */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//log.debug("Do post of  faculty ctl Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));	
			
		// Get Model
		FacultyModel fm = new FacultyModel();
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			FacultyBean fb = (FacultyBean) populateBean(request);
			
			try{
			if(id > 0){
				fm.update(fb);
				ServletUtility.setSuccessMessage("Successfully Updated", request);

			}else{
			long pk = fm.add(fb);
			System.out.println("Add Facu;lty ");
			ServletUtility.setSuccessMessage("Successfully Added", request);

		//		bean.setId(pk);
			}
			ServletUtility.setBean(fb, request);
			}catch(ApplicationException e){
				//log.error(e);
				ServletUtility.handleException(e, request, response);
				return ; 
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(fb, request);
				ServletUtility.setErrorMessage("Given Email ID already Exist", request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.FACULTY_CTL, request, response);
				return ;
			}			
	else if (OP_CANCEL.equalsIgnoreCase(op) ) {
		ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
		return ;
	}			
		System.out.println(" do post out ");
		ServletUtility.forward(getView(), request, response);
		//log.debug("Do post of  faculty ctl Ended");
	}

	@Override
	protected String getView() {
		return ORSView.FACULTY_VIEW;
	}
}
