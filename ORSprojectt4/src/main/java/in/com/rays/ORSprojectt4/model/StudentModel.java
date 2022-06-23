package in.com.rays.ORSprojectt4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.com.rays.ORSprojectt4.bean.CollegeBean;
import in.com.rays.ORSprojectt4.bean.StudentBean;
import in.com.rays.ORSprojectt4.exception.ApplicationException;
import in.com.rays.ORSprojectt4.exception.DatabaseException;
import in.com.rays.ORSprojectt4.exception.DuplicateRecordException;
import in.com.rays.ORSprojectt4.utility.JDBCDataSource;

public class StudentModel {
	//private static Logger log = Logger.getLogger(StudentModel.class);
    public Integer nextPK() throws DatabaseException {
      //  log.debug("Model nextPK Started");
        Connection conn = null;
        int pk = 0;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM ST_STUDENT");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                pk = rs.getInt(1);
            }
            rs.close();

        } catch (Exception e) {
            //log.error("Database Exception..", e);
            throw new DatabaseException("Exception : Exception in getting PK");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        //log.debug("Model nextPK End");
        return pk + 1;
    }
    public long add(StudentBean bean) throws ApplicationException,DuplicateRecordException {
       // log.debug("Model add Started");
        Connection conn = null;

        // get College Name
        CollegeModel cModel = new CollegeModel();
        CollegeBean collegeBean = cModel.findByPK(bean.getCollegeId());
        System.out.println(collegeBean.getName());
        bean.setCollegeName(collegeBean.getName());

     //   StudentBean duplicateName = findByEmailId(bean.getEmail());
        int pk = 0;

       // if (duplicateName != null) {
         //   throw new DuplicateRecordException("Email already exists");
        //}

        try {
            conn = JDBCDataSource.getConnection();
            pk = nextPK();
            // Get auto-generated next primary key
            System.out.println(pk + " in ModelJDBC");
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ST_STUDENT VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, pk);
            pstmt.setLong(2, bean.getCollegeId());
            pstmt.setString(3, bean.getCollegeName());
            pstmt.setString(4, bean.getFirstName());
            pstmt.setString(5, bean.getLastName());
            pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
            pstmt.setString(7, bean.getMobileNo());
            pstmt.setString(8, bean.getEmail());
            pstmt.setString(9, bean.getCreatedBy());
            pstmt.setString(10, bean.getModifiedBy());
            pstmt.setTimestamp(11, bean.getCreatedDatetime());
            pstmt.setTimestamp(12, bean.getModifiedDatetime());
            pstmt.executeUpdate();
            conn.commit(); // End transaction
            pstmt.close();
        } catch (Exception e) {
           // log.error("Database Exception..", e);
            try {
               // conn.rollback();
            } catch (Exception ex) {
            	ex.printStackTrace();
                throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
            }
            e.printStackTrace();
            throw new ApplicationException("Exception : Exception in add Student");
        } 
        //log.debug("Model add End");
        return pk;
    }
    public void delete(StudentBean bean) throws ApplicationException {
       // log.debug("Model delete Started");
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("DELETE FROM ST_STUDENT WHERE ID=?");
            pstmt.setLong(1, bean.getId());
            pstmt.executeUpdate();
            conn.commit(); // End transaction
            pstmt.close();

        } catch (Exception e) {
           // log.error("Database Exception..", e);
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException(
                        "Exception : Delete rollback exception "
                                + ex.getMessage());
            }
            throw new ApplicationException(
                    "Exception : Exception in delete Student");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
       // log.debug("Model delete Started");
    }
    public StudentBean findByEmailId(String Email) throws ApplicationException {
        //log.debug("Model findBy Email Started");
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM ST_STUDENT WHERE EMAIL=?");
        StudentBean bean = null;
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, Email);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = new StudentBean();
                bean.setId(rs.getLong(1));
                bean.setCollegeId(rs.getLong(2));
                bean.setCollegeName(rs.getString(3));
                bean.setFirstName(rs.getString(4));
                bean.setLastName(rs.getString(5));
                bean.setDob(rs.getDate(6));
                bean.setMobileNo(rs.getString(7));
                bean.setEmail(rs.getString(8));
                bean.setCreatedBy(rs.getString(9));
                bean.setModifiedBy(rs.getString(10));
                bean.setCreatedDatetime(rs.getTimestamp(11));
                bean.setModifiedDatetime(rs.getTimestamp(12));

            }
            rs.close();
        } catch (Exception e) {
           // log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting User by Email");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        //log.debug("Model findBy Email End");
        return bean;
    }
    public StudentBean findByPK(long pk) throws ApplicationException {
        //log.debug("Model findByPK Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM ST_STUDENT WHERE ID=?");
        StudentBean bean = null;
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, pk);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = new StudentBean();
                bean.setId(rs.getLong(1));
                bean.setCollegeId(rs.getLong(2));
                bean.setCollegeName(rs.getString(3));
                bean.setFirstName(rs.getString(4));
                bean.setLastName(rs.getString(5));
                bean.setDob(rs.getDate(6));
                bean.setMobileNo(rs.getString(7));
                bean.setEmail(rs.getString(8));
                bean.setCreatedBy(rs.getString(9));
                bean.setModifiedBy(rs.getString(10));
                bean.setCreatedDatetime(rs.getTimestamp(11));
                bean.setModifiedDatetime(rs.getTimestamp(12));
            }
            rs.close();
        } catch (Exception e) {
            //log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting User by pk");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        //log.debug("Model findByPK End");
        return bean;
    }
    public void update(StudentBean bean) throws ApplicationException,
            DuplicateRecordException {
        //log.debug("Model update Started");
        Connection conn = null;

        /*StudentBean beanExist = findByEmailId(bean.getEmail());

        // Check if updated Roll no already exist
        if (beanExist != null && beanExist.getId() != bean.getId()) {
            throw new DuplicateRecordException("Email Id is already exist");
        }
*/
        // get College Name
        CollegeModel cModel = new CollegeModel();
        CollegeBean collegeBean = cModel.findByPK(bean.getCollegeId());
        bean.setCollegeName(collegeBean.getName());

        try {

            conn = JDBCDataSource.getConnection();

            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn.prepareStatement("UPDATE ST_STUDENT SET COLLEGE_ID=?,COLLEGE_NAME=?,FIRST_NAME=?,LAST_NAME=?,DATE_OF_BIRTH=?,MOBILE_NO=?,EMAIL=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
            pstmt.setLong(1, bean.getCollegeId());
            pstmt.setString(2, bean.getCollegeName());
            pstmt.setString(3, bean.getFirstName());
            pstmt.setString(4, bean.getLastName());
            pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
            pstmt.setString(6, bean.getMobileNo());
            pstmt.setString(7, bean.getEmail());
            pstmt.setString(8, bean.getCreatedBy());
            pstmt.setString(9, bean.getModifiedBy());
            pstmt.setTimestamp(10, bean.getCreatedDatetime());
            pstmt.setTimestamp(11, bean.getModifiedDatetime());
            pstmt.setLong(12, bean.getId());
            pstmt.executeUpdate();
            conn.commit(); // End transaction
            pstmt.close();
        } catch (Exception e) {
            //log.error("Database Exception..", e);
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException( "Exception : Delete rollback exception "+ ex.getMessage());
            }
            throw new ApplicationException("Exception in updating Student ");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
       // log.debug("Model update End");
    }

    public List<StudentBean> search(StudentBean bean) throws ApplicationException {
        return search(bean, 0, 0);
    }
    public List<StudentBean> search(StudentBean bean, int pageNo, int pageSize)
            throws ApplicationException {
        //log.debug("Model search Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM ST_STUDENT WHERE 1=1");

        if (bean != null) {
            if (bean.getId() > 0) { sql.append(" AND id = " + bean.getId());
            }
            if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {sql.append(" AND FIRST_NAME like '" + bean.getFirstName()+ "%'");
            }
            if (bean.getLastName() != null && bean.getLastName().length() > 0) {sql.append(" AND LAST_NAME like '" + bean.getLastName() + "%'");
            }
            if (bean.getDob() != null && bean.getDob().getDate() > 0) {sql.append(" AND DOB = " + bean.getDob());
            }
            if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {sql.append(" AND MOBILE_NO like '" + bean.getMobileNo() + "%'");
            }
            if (bean.getEmail() != null && bean.getEmail().length() > 0) {sql.append(" AND EMAIL like '" + bean.getEmail() + "%'");
            }
            if (bean.getCollegeName() != null&& bean.getCollegeName().length() > 0) {sql.append(" AND COLLEGE_NAME = " + bean.getCollegeName());
            }

        }

        // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;

            sql.append(" Limit " + pageNo + ", " + pageSize);
            // sql.append(" limit " + pageNo + "," + pageSize);
        }

        ArrayList<StudentBean> list = new ArrayList<StudentBean>();
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = new StudentBean();
                bean.setId(rs.getLong(1));
                bean.setCollegeId(rs.getLong(2));
                bean.setCollegeName(rs.getString(3));
                bean.setFirstName(rs.getString(4));
                bean.setLastName(rs.getString(5));
                bean.setDob(rs.getDate(6));
                bean.setMobileNo(rs.getString(7));
                bean.setEmail(rs.getString(8));
                bean.setCreatedBy(rs.getString(9));
                bean.setModifiedBy(rs.getString(10));
                bean.setCreatedDatetime(rs.getTimestamp(11));
                bean.setModifiedDatetime(rs.getTimestamp(12));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
           // log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in search Student");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

       // log.debug("Model search End");
        return list;
    }
    public List<StudentBean> list() throws ApplicationException {
        return list(0, 0);
    }

    public List<StudentBean> list(int pageNo, int pageSize) throws ApplicationException {
       // log.debug("Model list Started");
        ArrayList<StudentBean> list = new ArrayList<StudentBean>();
        StringBuffer sql = new StringBuffer("select * from ST_STUDENT");
        // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;
            sql.append(" limit " + pageNo + "," + pageSize);
        }

        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                StudentBean bean = new StudentBean();
                bean.setId(rs.getLong(1));
                bean.setCollegeId(rs.getLong(2));
                bean.setCollegeName(rs.getString(3));
                bean.setFirstName(rs.getString(4));
                bean.setLastName(rs.getString(5));
                bean.setDob(rs.getDate(6));
                bean.setMobileNo(rs.getString(7));
                bean.setEmail(rs.getString(8));
                bean.setCreatedBy(rs.getString(9));
                bean.setModifiedBy(rs.getString(10));
                bean.setCreatedDatetime(rs.getTimestamp(11));
                bean.setModifiedDatetime(rs.getTimestamp(12));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
           // log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting list of Student");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
       // log.debug("Model list End");
        return list;
    
    }
}