package in.com.ORSprojectt4.testModel;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.com.rays.ORSprojectt4.bean.RoleBean;
import in.com.rays.ORSprojectt4.exception.ApplicationException;
import in.com.rays.ORSprojectt4.exception.DuplicateRecordException;
import in.com.rays.ORSprojectt4.model.RoleModel;

public class RoleModelTest {
	public static RoleModel model = new RoleModel();

	public static void main(String[] args) throws ParseException, ApplicationException, DuplicateRecordException {
		// testAdd();
		// testDelete();
		// testUpdate();
		// testFindByPK();
		// testFindByName();
		// testSearch();
		 testList();

	}

	public static void testAdd() throws ParseException, ApplicationException {

		try {
			RoleBean bean = new RoleBean();
			// SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

			// bean.setId(1L);
			bean.setName("disha");
			bean.setDescription("pamnani");
			bean.setModifiedBy("---");
			bean.setCreatedBy("yashita");
			long pk = model.add(bean);
			RoleBean addedbean = model.findByPK(pk);
			if (addedbean == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	public static void testDelete() {

		try {
			RoleBean bean = new RoleBean();
			long pk = 2L;
			bean.setId(pk);
			model.delete(bean);
			RoleBean deletedbean = model.findByPK(pk);
			if (deletedbean != null) {
				System.out.println("Test Delete fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testUpdate() throws ApplicationException, DuplicateRecordException {
		RoleBean bean = new RoleBean();
		RoleModel model = new RoleModel();

		bean.setId(2);
		bean.setName("ayushmaan");
		bean.setDescription("student");
		bean.setCreatedBy("yashita");
		bean.setModifiedBy("yashu");

		model.update(bean);
	}

	private static void testFindByPK() throws ApplicationException {
		RoleBean bean = new RoleBean();
		long pk = 3L;
		bean = model.findByPK(pk);
		if (bean == null) {
			System.out.println("Test Find By PK fail");
		}
		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getDescription());

	}

	public static void testFindByName() throws ApplicationException {
		RoleBean bean = new RoleBean();
		RoleModel model = new RoleModel();
		bean = model.findByName("nayna");
		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getDescription());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());

	}

	public static void testSearch() throws ApplicationException {
		RoleBean bean = new RoleBean();
		RoleModel model = new RoleModel();
		List list = new ArrayList();
		list = model.search(bean);

		Iterator it = list.iterator();
		while (it.hasNext()) {
			RoleBean bean1 = (RoleBean) it.next();
			System.out.println(bean1.getId());
			System.out.println(bean1.getName());
			System.out.println(bean1.getDescription());
			System.out.println(bean1.getCreatedBy());
			System.out.println(bean1.getModifiedBy());
			System.out.println(bean1.getCreatedDatetime());
			System.out.println(bean1.getModifiedDatetime());
			System.out.println("................................");

		}

	}

	public static void testList() throws ApplicationException {

		RoleBean bean = new RoleBean();
		List list = new ArrayList();
		list = model.list(1, 10);
		if (list.size() < 0) {
			System.out.println("Test list fail");
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (RoleBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getDescription());
			}

		}
	}
}
