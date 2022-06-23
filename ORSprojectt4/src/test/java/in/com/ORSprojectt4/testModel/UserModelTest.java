package in.com.ORSprojectt4.testModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import in.com.rays.ORSprojectt4.bean.UserBean;
import in.com.rays.ORSprojectt4.exception.ApplicationException;
import in.com.rays.ORSprojectt4.exception.DuplicateRecordException;
import in.com.rays.ORSprojectt4.model.UserModel;

public class UserModelTest {
	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
		// testAdd();
		// testDelete();
		// testUpdate();
		// testFindByPK();
		// testFindByLogin();
		// testSearch();
		// testGetRoles();
		// testList();
		// testAuthenticate();
		 testRegisterUser();
		// testchangePassword();
		// testforgetPassword();
		// testresetPassword();

	}

	private static void testRegisterUser() {
		UserBean bean = new UserBean();
		UserModel model = new UserModel();
		
		
		
	}

	private static void testAuthenticate() throws Exception {
		UserBean bean = new UserBean();
		UserModel model = new UserModel();
        bean.setLogin("ray@gmail.com");
        bean.setPassword("pass1234");
        bean = model.authenticate(bean.getLogin(), bean.getPassword());
        if (bean != null) {
            System.out.println("Successfully login");

        } else {
            System.out.println("Invalied login Id & password");
        }
	}

	private static void testList() throws ApplicationException {
		UserBean bean = new UserBean();
		UserModel model = new UserModel();
		
		List list = new ArrayList();
		list = model.list();
		
			Iterator it = list.iterator();
			while (it.hasNext()) {
				UserBean bean1 = (UserBean) it.next();
				System.out.println(bean1.getId());
				System.out.println(bean1.getFirstName());
				System.out.println(bean1.getLastName());
				System.out.println(bean1.getLogin());
				System.out.println(bean1.getPassword());
				System.out.println(bean1.getRoleId());
				System.out.println(bean1.getGender());
				System.out.println(bean1.getConfirmPassword());
				System.out.println(bean1.getModifiedBy());
				System.out.println(bean1.getCreatedBy());
				System.out.println(" ");
				
			}
		
	}

	private static void testSearch() throws ApplicationException {
		UserBean bean = new UserBean();
		UserModel model = new UserModel();
		
		List list = new ArrayList();
		list = model.search(bean);
		Iterator it = list.iterator();
		
		while (it.hasNext()) {
			bean = (UserBean) it.next();
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getGender());
			System.out.println(bean.getConfirmPassword());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedBy());
			
		}
		
	}

	private static void testFindByLogin() throws ApplicationException {
		UserBean bean = new UserBean();
		UserModel model = new UserModel();
		
		String login ="ray@gmail.com";
		
		bean = model.findByLogin(login);
		System.out.println("find by login="+bean.getLogin());
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getPassword());
		System.out.println(bean.getRoleId());
		//System.out.println(bean.getUnSuccessfulLogin());
		System.out.println(bean.getGender());
		//System.out.println(bean.getLock());
		System.out.println(bean.getConfirmPassword());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedBy());
		
	}

	private static void testFindByPK() throws ApplicationException {
		UserBean bean = new UserBean();
		UserModel model = new UserModel();
		
		long pk = 2l;
		bean = model.findByPK(pk);
		System.out.println("find by pk="+bean.getId());
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getLogin());
		System.out.println(bean.getPassword());
		System.out.println(bean.getRoleId());
		//System.out.println(bean.getUnSuccessfulLogin());
		System.out.println(bean.getGender());
		//System.out.println(bean.getLock());
		System.out.println(bean.getConfirmPassword());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedBy());
		
	}

	private static void testUpdate() throws ApplicationException, DuplicateRecordException {
		UserBean bean = new UserBean();
		UserModel model = new UserModel();
		
		bean.setId(1);
		bean.setFirstName("ray");
		bean.setLastName("verma");
		bean.setLogin("ray@gmail.com");
		bean.setPassword("pass1234");
		bean.setRoleId(1L);
		//bean.setUnSuccessfulLogin(2);
		bean.setGender("female");
		//bean.setLock("Yes");
		bean.setConfirmPassword("pass1234");
		
		model.update(bean);
		
		System.out.println("record updated");
		
	}

	private static void testDelete() throws ApplicationException {
		UserBean bean = new UserBean();
		UserModel model = new UserModel();
		
		bean.setId(2l);
		
		model.delete(bean);
		System.out.println("record deleted");
		
	}

	private static void testAdd() throws ApplicationException, DuplicateRecordException {
		UserBean bean = new UserBean();
		UserModel model = new UserModel();

		bean.setId(1);
		bean.setFirstName("nikkhat");
		bean.setLastName("verma");
		bean.setLogin("nikkhat@gmail.com");
		bean.setPassword("pass1234");
		bean.setRoleId(2L);
		//bean.setUnSuccessfulLogin(2);
		bean.setGender("female");
		//bean.setLock("Yes");
		bean.setConfirmPassword("pass1234");
		
		model.add(bean);
		
		System.out.println("record added");
	}

}
