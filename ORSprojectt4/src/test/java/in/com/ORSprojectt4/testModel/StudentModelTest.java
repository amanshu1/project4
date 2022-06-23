package in.com.ORSprojectt4.testModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.com.rays.ORSprojectt4.bean.StudentBean;
import in.com.rays.ORSprojectt4.exception.ApplicationException;
import in.com.rays.ORSprojectt4.exception.DuplicateRecordException;
import in.com.rays.ORSprojectt4.model.StudentModel;

public class StudentModelTest {
	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
		 testAdd();
		// testDelete();
		// testUpdate();s
		// testFindByPK();
		// testFindByEmailId();
		// testSearch();
		// testList();
	}

	private static void testList() throws ApplicationException {
		StudentBean bean = new StudentBean();
		StudentModel model = new StudentModel();
		List list = new ArrayList();
		list = model.list(1, 10);
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (StudentBean) it.next();
				System.out.println(bean.getCollegeId());
				System.out.println(bean.getFirstName());
				System.out.println(bean.getLastName());
				System.out.println(bean.getEmail());
				System.out.println(bean.getMobileNo());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getCreatedBy());
			}
	}

	private static void testSearch() throws ApplicationException {
		StudentBean bean = new StudentBean();
		StudentModel model = new StudentModel();
		List list = new ArrayList();
		list = model.search(bean);

		Iterator it = list.iterator();
		while (it.hasNext()) {
			StudentBean bean1 = (StudentBean) it.next();
			System.out.println(bean1.getCollegeId());
			System.out.println(bean1.getFirstName());
			System.out.println(bean1.getLastName());
			System.out.println(bean1.getEmail());
			System.out.println(bean1.getMobileNo());
			System.out.println(bean1.getModifiedBy());
			System.out.println(bean1.getCreatedBy());
		}

	}

	private static void testFindByEmailId() throws ApplicationException {
		StudentBean bean = new StudentBean();
		StudentModel model = new StudentModel();

		String p = "nivedi@gmail.com";
		bean = model.findByEmailId(p);

		System.out.println(bean.getCollegeId());
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getEmail());
		System.out.println(bean.getMobileNo());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedBy());

		System.out.println("find by email id");

	}

	private static void testFindByPK() throws ApplicationException {
		StudentBean bean = new StudentBean();
		StudentModel model = new StudentModel();

		long pk = 2L;
		bean = model.findByPK(pk);
		if (bean == null) {
			System.out.println("Test Find By PK fail");
		}
		System.out.println(bean.getCollegeId());
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getEmail());
		System.out.println(bean.getMobileNo());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedBy());

		System.out.println("find by pk");

	}

	private static void testUpdate() throws ApplicationException, DuplicateRecordException {
		StudentBean bean = new StudentBean();
		StudentModel model = new StudentModel();

		bean.setCollegeId(2l);
		bean.setFirstName("shristi");
		bean.setLastName("vermaa");
		bean.setEmail("shristi@gmail.com");
		bean.setMobileNo("786543218");
		bean.setCreatedBy("yashu");
		bean.setModifiedBy("yashita");

		model.update(bean);
		System.out.println("record updated");

	}

	private static void testDelete() throws ApplicationException {
		StudentBean bean = new StudentBean();
		StudentModel model = new StudentModel();

		bean.setId(2l);

		model.delete(bean);
		System.out.println("record deleted");
	}

	private static void testAdd() throws ApplicationException, DuplicateRecordException {
		StudentBean bean = new StudentBean();
		StudentModel model = new StudentModel();

		bean.setCollegeId(8l);
		bean.setFirstName("ankita");
		bean.setLastName("shrivastava");
		bean.setEmail("ankita@gmail.com");
		bean.setMobileNo("855412356");
		bean.setCreatedBy("yashita");
		bean.setModifiedBy("---");

		model.add(bean);
		System.out.println("record added");
	}

}
