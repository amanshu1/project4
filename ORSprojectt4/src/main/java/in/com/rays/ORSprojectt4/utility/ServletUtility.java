package in.com.rays.ORSprojectt4.utility;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.com.rays.ORSprojectt4.bean.BaseBean;
import in.com.rays.ORSprojectt4.model.AppRole;
import in.com.rays.ORSprojectt4.model.UserModel;
import in.com.rays.ORSprojectt4.servlet.BaseCtl;
import in.com.rays.ORSprojectt4.servlet.ORSView;

public class ServletUtility {
	public static void forward(String page, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request, response);
    }

    public static void forwardView(String page, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        request.setAttribute("bodyPage", page);
        RequestDispatcher rd = request.getRequestDispatcher(ORSView.LAYOUT_VIEW);
        rd.forward(request, response);
    }

    public static void redirect(String page, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        response.sendRedirect(page);
    }

    public static void handleException(Exception e, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("exception", e);
        response.sendRedirect(ORSView.ERROR_CTL);

    }

    public static String getErrorMessage(String property,
            HttpServletRequest request) {

        String val = (String) request.getAttribute(property);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }

    public static String getErrorMessageHtml(HttpServletRequest request) {

        Enumeration<String> e = request.getAttributeNames();

        StringBuffer sb = new StringBuffer("<UL>");
        String name = null;

        while (e.hasMoreElements()) {
            name = e.nextElement();
            if (name.startsWith("error.")) {
                sb.append("<LI class='error'>" + request.getAttribute(name)
                        + "</LI>");
            }
        }
        sb.append("</UL>");
        return sb.toString();
    }

    public static String getMessage(String property, HttpServletRequest request) {
        String val = (String) request.getAttribute(property);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }

    public static void setErrorMessage(String msg, HttpServletRequest request) {
        request.setAttribute(BaseCtl.MSG_ERROR, msg);
    }

    public static String getErrorMessage(HttpServletRequest request) {
        String val = (String) request.getAttribute(BaseCtl.MSG_ERROR);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }

    public static void setSuccessMessage(String msg, HttpServletRequest request) {
        request.setAttribute(BaseCtl.MSG_SUCCESS, msg);
    }

    public static String getSuccessMessage(HttpServletRequest request) {
        String val = (String) request.getAttribute(BaseCtl.MSG_SUCCESS);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }

   /* public static void setModel(BaseModel model, HttpServletRequest request) {
        request.setAttribute("model", model);
    }*/

    public static void setBean(BaseBean bean, HttpServletRequest request) {
        request.setAttribute("bean", bean);
    }

    public static void setUserModel(UserModel model, HttpServletRequest request) {
        request.getSession().setAttribute("user", model);
    }

    public static BaseBean getBean(HttpServletRequest request) {
        return (BaseBean) request.getAttribute("bean");
    }

    public static boolean isLoggedIn(HttpServletRequest request) {
        UserModel model = (UserModel) request.getSession().getAttribute("user");
        return model != null;
    }

    public static long getRole(HttpServletRequest request) {
        UserModel model = (UserModel) request.getSession().getAttribute("user");
        if (model != null) {
            return model.getRoleId();
        } else {
            return AppRole.GUEST;
        }
    }

   /* public static BaseModel getModel(HttpServletRequest request) {
        return (BaseModel) request.getAttribute("model");
    }
*/
    public static String getParameter(String property,
            HttpServletRequest request) {
        String val = (String) request.getParameter(property);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }

    public static void setList(List list, HttpServletRequest request) {
        request.setAttribute("list", list);
    }
    public static List getList(HttpServletRequest request) {
        return (List) request.getAttribute("list");
    }

    public static void setPageNo(int pageNo, HttpServletRequest request) {
        request.setAttribute("pageNo", pageNo);
    }

    public static int getPageNo(HttpServletRequest request) {
        return (int) request.getAttribute("pageNo");
    }
    public static void setPageSize(int pageSize, HttpServletRequest request) {
        request.setAttribute("pageSize", pageSize);
    }
    public static int getPageSize(HttpServletRequest request) {
        return (int) request.getAttribute("pageSize");
    }

}
