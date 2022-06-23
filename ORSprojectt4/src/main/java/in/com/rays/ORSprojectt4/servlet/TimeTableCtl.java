package in.com.rays.ORSprojectt4.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.com.rays.ORSprojectt4.bean.CourseBean;
import in.com.rays.ORSprojectt4.bean.SubjectBean;
import in.com.rays.ORSprojectt4.bean.TimeTableBean;
import in.com.rays.ORSprojectt4.exception.ApplicationException;
import in.com.rays.ORSprojectt4.exception.DuplicateRecordException;
import in.com.rays.ORSprojectt4.model.CourseModel;
import in.com.rays.ORSprojectt4.model.SubjectModel;
import in.com.rays.ORSprojectt4.model.TimeTableModel;
import in.com.rays.ORSprojectt4.utility.DataUtility;
import in.com.rays.ORSprojectt4.utility.DataValidator;
import in.com.rays.ORSprojectt4.utility.PropertyReader;
import in.com.rays.ORSprojectt4.utility.ServletUtility;

@WebServlet(name = "TimeTableCtl", urlPatterns ={"/ctl/TimeTableCtl"})
public class TimeTableCtl extends BaseCtl{
	//private static Logger log = Logger.getLogger(TimeTableCtl.class);

	protected void preload(HttpServletRequest request) {
		CourseModel cmodel = new CourseModel();
		SubjectModel smodel = new SubjectModel();
		List<CourseBean> clist = new ArrayList<CourseBean>();
		List<SubjectBean> slist = new ArrayList<SubjectBean>();
		try {
			clist = cmodel.list();
			slist = smodel.list();
			request.setAttribute("CourseList", clist);
			request.setAttribute("SubjectList", slist);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean validate(HttpServletRequest request) {
		//log.debug("validate method of TimeTable Ctl started");
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("courseId"))) {
			request.setAttribute("courseId", PropertyReader.getValue("error.require", "Course"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("subjectId"))) {
			request.setAttribute("subjectId", PropertyReader.getValue("error.require", "Subject"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("semester"))) {
			request.setAttribute("semester", PropertyReader.getValue("error.require", "Semester"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("ExDate"))) {
			request.setAttribute("ExDate", PropertyReader.getValue("error.require", "Exam Date"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("ExTime"))) {
			request.setAttribute("ExTime", PropertyReader.getValue("error.require", "Exam Time"));
			pass = false;
		}
		
		/*if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", "Please Select Description");
			pass = false;
		}*/

		//log.debug("validate method of TimeTable Ctl End");
		return pass;
	}

	protected TimeTableBean populateBean(HttpServletRequest request) {
		//log.debug("populateBean method of TimeTable Ctl start");
		TimeTableBean bean = new TimeTableBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		
		bean.setSubjectId(DataUtility.getInt(request.getParameter("subjectId")));
	//	bean.setSubjectName(DataUtility.getString(request.getParameter("subjectname")));
		bean.setCourseId(DataUtility.getInt(request.getParameter("courseId")));
//		bean.setCourseName(DataUtility.getString(request.getParameter("coursename")));
		bean.setSemester(DataUtility.getString(request.getParameter("semester")));
	//	bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setExamDate(DataUtility.getDate(request.getParameter("ExDate")));
		bean.setExamTime(DataUtility.getString(request.getParameter("ExTime")));
		System.out.println("<<<<<<__________>>>>>>>>");
		System.out.println(request.getParameter("ExDate")+  bean.getExamDate());
		System.out.println("<<<<<<__________>>>>>>>>");
		populateDTO(bean, request);
		//log.debug("populateBean method of TimeTable Ctl End");
		return bean;
	}
    /**
     * Contains Display logics
     */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//log.debug("do Get method of TimeTable Ctl Started");
	//System.out.println("Timetable ctl .do get started........>>>>>");

//		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
	
		TimeTableModel model = new TimeTableModel();
		TimeTableBean bean = null;
		if (id > 0) {
			try {
				bean = model.findByPk(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				//log.error(e);
				ServletUtility.handleException(e, request, response);
			}
		}

		//log.debug("do Get method of TimeTable Ctl End");
		System.out.println("Timetable ctl .do get End........>>>>>");
		ServletUtility.forward(getView(), request, response);
	}
    /**
     * Contains Submit logics
     */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//log.debug("do post method of TimeTable Ctl start");

		List list;
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		TimeTableModel model = new TimeTableModel();
	
		
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) 
		{
			TimeTableBean bean = (TimeTableBean)populateBean(request);
			System.out.println(bean.getCourseId());
			try {
				if(id>0){
					model.update(bean);
					ServletUtility.setSuccessMessage("Successfully Updated", request);

				}else{
				model.add(bean);
				ServletUtility.setSuccessMessage("Successfully Saved", request);

				} 
				ServletUtility.setBean(bean, request);
				
			}catch (ApplicationException e) {
				//log.error(e);
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
			} catch (DuplicateRecordException e) {
				e.printStackTrace();
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Time Table already Exists", request);
			}
		}
		else if (OP_CANCEL.equalsIgnoreCase(op))
		{
			ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
			return;
		}
		else if ( OP_RESET.equalsIgnoreCase(op))
		{
			ServletUtility.redirect(ORSView.TIMETABLE_CTL, request, response);
			return;
		}
		
	
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.TIMETABLE_VIEW;
	}
}
