package in.com.ORSprojectt4.testModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.com.rays.ORSprojectt4.bean.MarksheetBean;
import in.com.rays.ORSprojectt4.exception.ApplicationException;
import in.com.rays.ORSprojectt4.exception.DuplicateRecordException;
import in.com.rays.ORSprojectt4.model.MarksheetModel;

public class MarksheetModelTest {
	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
        // testAdd();
        // testDelete();
        // testUpdate();
        // testFindByRollNo();
        // testFindByPK();
        // testSearch();
        // testMeritList();
         testList();

    }

	private static void testList() throws ApplicationException {
		MarksheetBean bean = new MarksheetBean();
		MarksheetModel model = new MarksheetModel();
		List list = new ArrayList();
		list = model.list();
		
			System.out.println("Test list fail");
			Iterator it = list.iterator();
			while (it.hasNext()) {
				MarksheetBean bean1 = (MarksheetBean) it.next();
				System.out.println(bean1.getId());
				System.out.println(bean1.getRollNo());
	            System.out.println(bean1.getName());
	            System.out.println(bean1.getPhysics());
	            System.out.println(bean1.getChemistry());
	            System.out.println(bean1.getMaths());
			
			
		}
	}

	private static void testMeritList() throws ApplicationException {
		   MarksheetBean bean = new MarksheetBean();
		   MarksheetModel model = new MarksheetModel();
           List list = new ArrayList();
           list = model.getMeritList(1, 5);
           Iterator it = list.iterator();
           while (it.hasNext()) {
               bean = (MarksheetBean) it.next();
               System.out.println(bean.getId());
               System.out.println(bean.getRollNo());
               System.out.println(bean.getName());
               System.out.println(bean.getPhysics());
               System.out.println(bean.getChemistry());
               System.out.println(bean.getMaths());
           }
		
		
	}

	private static void testSearch() throws ApplicationException {
		MarksheetBean bean = new MarksheetBean();
		MarksheetModel model = new MarksheetModel();
		
		List list = new ArrayList();
		list = model.search(bean);
		Iterator it = list.iterator();
		
		while (it.hasNext()) {
			bean = (MarksheetBean) it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getRollNo());
            System.out.println(bean.getName());
            System.out.println(bean.getPhysics());
            System.out.println(bean.getChemistry());
            System.out.println(bean.getMaths());	
		}
		
	}

	private static void testFindByPK() throws ApplicationException {
		MarksheetBean bean = new MarksheetBean();
		MarksheetModel model = new MarksheetModel();
		
		long pk = 2l;
		bean = model.findByPK(pk);
		System.out.println("find by pk="+bean.getId());
		System.out.println(bean.getRollNo());
		System.out.println(bean.getName());
		System.out.println(bean.getChemistry());
		System.out.println(bean.getPhysics());
		System.out.println(bean.getMaths());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedBy());
		
	}

	private static void testFindByRollNo() throws ApplicationException {
		MarksheetBean bean = new MarksheetBean();
		MarksheetModel model = new MarksheetModel();
		
		String RollNo = "ray101";
		bean = model.findByRollNo(RollNo);
		System.out.println("find by roll no");
		System.out.println(bean.getRollNo());
		System.out.println(bean.getName());
		System.out.println(bean.getChemistry());
		System.out.println(bean.getPhysics());
		System.out.println(bean.getMaths());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedBy());
	}

	private static void testUpdate() throws ApplicationException, DuplicateRecordException {
		MarksheetBean bean = new MarksheetBean();
		MarksheetModel model = new MarksheetModel();
		bean.setId(1l);
		bean.setStudentId(1l);
		bean.setName("ray");
		bean.setPhysics(45);
		bean.setChemistry(56);
		bean.setMaths(72);
		bean.setRollNo("ray101");
		bean.setCreatedBy("yashita");
		bean.setModifiedBy("---");
		
		model.update(bean);
		System.out.println("record updated");

		
	}

	private static void testDelete() throws ApplicationException {
		MarksheetBean bean = new MarksheetBean();
		MarksheetModel model = new MarksheetModel();
		
		bean.setId(2l);
		
		model.delete(bean);
		System.out.println("record deleted");
		
	}

	private static void testAdd() throws ApplicationException, DuplicateRecordException {
		MarksheetBean bean = new MarksheetBean();
		MarksheetModel model = new MarksheetModel();
		
		bean.setName("shrishti");
		bean.setStudentId(2l);
		bean.setPhysics(89);
		bean.setChemistry(86);
		bean.setMaths(82);
		bean.setRollNo("ray102");
		bean.setCreatedBy("yashita");
		bean.setModifiedBy("---");
		
		model.add(bean);
		
		System.out.println("record added");
	}
}
