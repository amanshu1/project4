package in.com.ORSprojectt4.testModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.com.rays.ORSprojectt4.bean.CollegeBean;
import in.com.rays.ORSprojectt4.exception.ApplicationException;
import in.com.rays.ORSprojectt4.exception.DuplicateRecordException;
import in.com.rays.ORSprojectt4.model.CollegeModel;

public class CollegeModelTest {
	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
		// testAdd();
		// testDelete();
		// testUpdate();
		// testFindByName(); //in.com.ORSprojectt4.testModal.StudentModelTest
		// testFindByPK();
		// testSearch();
		 testList();
	}

	private static void testList() throws ApplicationException {
		CollegeBean bean = new CollegeBean();
		CollegeModel model = new CollegeModel(); 
		List list = new ArrayList();
		list = model.list(1, 10);
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (CollegeBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getAddress());
				System.out.println(bean.getCity());
				System.out.println(bean.getState());
				System.out.println(bean.getPhoneNo());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getModifiedBy());
		}
	}

	private static void testSearch() throws ApplicationException {
		CollegeBean bean = new CollegeBean();
		CollegeModel model = new CollegeModel();
		List list = new ArrayList();
		list = model.search(bean);

		Iterator it = list.iterator();
		while (it.hasNext()) {
			CollegeBean bean1 = (CollegeBean) it.next();
			System.out.println(bean1.getId());
			System.out.println(bean1.getName());
			System.out.println(bean1.getAddress());
			System.out.println(bean1.getCity());
			System.out.println(bean1.getState());
			System.out.println(bean1.getPhoneNo());
			System.out.println(bean1.getCreatedBy());
			System.out.println(bean1.getModifiedBy());
		}

	}

	private static void testFindByPK() throws ApplicationException {
		CollegeBean bean = new CollegeBean();
		CollegeModel model = new CollegeModel();
		long pk = 3L;
		bean = model.findByPK(pk);
		if (bean == null) {
			System.out.println("Test Find By PK fail");
		}
		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getPhoneNo());
		System.out.println(bean.getState());
		System.out.println(bean.getCity());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedBy());

		System.out.println("find by pk");

	}

	private static void testFindByName() throws ApplicationException {
		CollegeBean bean = new CollegeBean();
		CollegeModel model = new CollegeModel();
		String name = "ray";
		bean = model.findByName(name);
		
		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getPhoneNo());
		System.out.println(bean.getState());
		System.out.println(bean.getCity());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedBy());

	}

	private static void testUpdate() throws ApplicationException, DuplicateRecordException {
		CollegeBean bean = new CollegeBean();
		CollegeModel model = new CollegeModel();
		bean.setId(3);
		bean.setName("kamla");
		bean.setAddress("174 day dream apartment");
		bean.setCity("mumbai");
		bean.setState("maharastra");
		bean.setPhoneNo("9665412352");
		bean.setCreatedBy("yashita");
		bean.setModifiedBy("yashu");

		model.update(bean);
		System.out.println("updated");

	}

	private static void testDelete() throws ApplicationException {
		CollegeBean bean = new CollegeBean();
		CollegeModel model = new CollegeModel();
		bean.setId(3);
		model.delete(bean);
		System.out.println("deleted");
	}

	private static void testAdd() throws ApplicationException, DuplicateRecordException {
		CollegeBean bean = new CollegeBean();
		CollegeModel model = new CollegeModel();

		bean.setName("emily");
		bean.setAddress("76,poughkeepsie");
		bean.setCity("poughkeepsie");
		bean.setState("new york");
		bean.setPhoneNo("789781235");
		bean.setCreatedBy("yashita");
		bean.setModifiedBy("---");

		model.add(bean);
		System.out.println("added");
	}

}
