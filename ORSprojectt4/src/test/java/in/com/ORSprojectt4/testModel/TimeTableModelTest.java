package in.com.ORSprojectt4.testModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.com.rays.ORSprojectt4.bean.TimeTableBean;
import in.com.rays.ORSprojectt4.exception.ApplicationException;
import in.com.rays.ORSprojectt4.exception.DuplicateRecordException;
import in.com.rays.ORSprojectt4.model.TimeTableModel;

public class TimeTableModelTest {
	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
		//testAdd();
		//testUpdate();
		//testDelete();
		//testSearch();
		testList();
	}

	public static void testAdd() throws ApplicationException, DuplicateRecordException {
		TimeTableBean bean = new TimeTableBean();
		TimeTableModel model = new TimeTableModel();
		bean.setCourseId(1);
		bean.setCourseName("b.tech");
		bean.setSubjectName("physics");
		bean.setExamDate(null);
		bean.setSemester("1st semester");
		model.add(bean);
		System.out.println("added");
		
	}

	public static void testUpdate() throws ApplicationException, DuplicateRecordException {
		TimeTableBean bean = new TimeTableBean();
		TimeTableModel model = new TimeTableModel();
		bean.setCourseId(1);
		bean.setId(1);
		bean.setCourseName("b.tech");
		bean.setSubjectName("maths");
		bean.setExamDate(null);
		bean.setSemester("1st semester");
		model.update(bean);
		System.out.println("updated");
		
	}

	public static void testDelete() throws ApplicationException {
		TimeTableBean bean = new TimeTableBean();
		TimeTableModel model = new TimeTableModel();
		bean.setId(2);
		model.delete(bean);
		System.out.println("deleted");
	}

	public static void testSearch() throws ApplicationException {
		TimeTableBean bean = new TimeTableBean();
		TimeTableModel model = new TimeTableModel();
		List list = new ArrayList();
		list = model.search(bean);
		Iterator<TimeTableBean> it = list.iterator();
		while (it.hasNext()) {
			TimeTableBean bean1 = (TimeTableBean) it.next();
			System.out.println(bean1.getCourseId());
			System.out.println(bean1 .getCourseName());
			System.out.println(bean1.getSemester());
			System.out.println(bean1.getSubjectName());
			System.out.println(bean1.getExamDate());
			System.out.println(bean1.getExamTime());
			System.out.println("....................................");
			
		}
		
	}

	public static void testList()throws ApplicationException {
		TimeTableBean bean = new TimeTableBean();
		TimeTableModel model = new TimeTableModel();
		List list = new ArrayList();
		list = model.list();
		Iterator<TimeTableBean> it = list.iterator();
		while (it.hasNext()) {
			TimeTableBean bean1 = (TimeTableBean) it.next();
			System.out.println(bean1.getCourseId());
			System.out.println(bean1 .getCourseName());
			System.out.println(bean1.getSemester());
			System.out.println(bean1.getSubjectName());
			System.out.println(bean1.getExamDate());
			System.out.println(bean1.getExamTime());
			System.out.println("....................................");
		
	}

}

}
