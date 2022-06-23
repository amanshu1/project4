package in.com.ORSprojectt4.testModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.com.rays.ORSprojectt4.bean.CourseBean;
import in.com.rays.ORSprojectt4.exception.ApplicationException;
import in.com.rays.ORSprojectt4.exception.DuplicateRecordException;
import in.com.rays.ORSprojectt4.model.CourseModel;

public class CourseModelTest {
	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
		// testAdd();
		// testUpdate();
		// testDelete();
		 testSearch();
		// testList();
	}

	public static void testList() throws ApplicationException {
		CourseBean bean = new CourseBean();
		CourseModel model = new CourseModel();
		List list = new ArrayList();
		list = model.list();
		Iterator<CourseBean> it = list.iterator();
		while (it.hasNext()) {
			CourseBean bean1 = (CourseBean) it.next();
			System.out.println(bean1.getCourseName());
			System.out.println(bean1.getDescription());
			System.out.println(bean1.getModifiedBy());
			System.out.println("..................................");

		}

	}

	public static void testSearch() throws ApplicationException {
		CourseBean bean = new CourseBean();
		CourseModel model = new CourseModel();
		List list = new ArrayList();
		list = model.search(bean);
		Iterator<CourseBean> it = list.iterator();
		while (it.hasNext()) {
			CourseBean bean1 = (CourseBean) it.next();
			System.out.println(bean1.getCourseName());
			System.out.println(bean1.getDescription());
			System.out.println(bean1.getModifiedBy());
			System.out.println("..................................");

		}

	}

	public static void testDelete() throws ApplicationException {
		CourseBean bean = new CourseBean();
		CourseModel model = new CourseModel();
		bean.setId(1);
		model.delete(bean);
		System.out.println("DELETED");

	}

	public static void testUpdate() throws ApplicationException, DuplicateRecordException {
		CourseBean bean = new CourseBean();
		CourseModel model = new CourseModel();
		bean.setCourseName("BBA");
		bean.setDescription("Management");
		bean.setId(1);
		model.update(bean);
		System.out.println("UPDATED");

	}

	public static void testAdd() throws ApplicationException, DuplicateRecordException {
		CourseBean bean = new CourseBean();
		CourseModel model = new CourseModel();
		bean.setCourseName("B-tech");
		bean.setDescription("IP");
		model.add(bean);
		System.out.println("added");

	}

}
