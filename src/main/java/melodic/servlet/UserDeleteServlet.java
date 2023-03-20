package melodic.servlet;

import melodic.dal.*;
import melodic.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/userdelete")
public class UserDeleteServlet extends HttpServlet {
	
	protected StandardUserDao standardUserDao;
	protected PremiumUserDao premiumUserDao;
	protected SingerDao singerDao;
	@Override
	public void init() throws ServletException {
		standardUserDao = StandardUserDao.getInstance();
		premiumUserDao = PremiumUserDao.getInstance();
		singerDao = SingerDao.getInstance();
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete User");        
        req.getRequestDispatcher("/UserDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        boolean isDeleted = false;
        // Retrieve and validate userId.
        int userId = Integer.parseInt(req.getParameter("userid"));
        if (userId < 0) {
            messages.put("title", "Invalid userId");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the StandardUser.
        	StandardUser standardUser = new StandardUser(userId);
	        try {
	        	standardUser = standardUserDao.delete(standardUser);
	        	// Update the message.
		        if (standardUser == null) {
		            messages.put("title", "Successfully deleted UserId: " + userId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete UserId: " + userId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				
	        }
	       
	        if(!isDeleted) {
	        	 //Search in PremiumUser
		        PremiumUser premiumUser = new PremiumUser(userId);
		        try {
		        	premiumUser = premiumUserDao.delete(premiumUser);
		        	// Update the message.
			        if (premiumUser == null) {
			            messages.put("title", "Successfully deleted UserId: " + userId);
			            messages.put("disableSubmit", "true");
			        } else {
			        	messages.put("title", "Failed to delete UserId: " + userId);
			        	messages.put("disableSubmit", "false");
			        }
		        } catch (SQLException e) {
		        }
	        }
	        if(!isDeleted) {
	        	 //Search in Singer
		        Singer singer = new Singer(userId);
		        try {
		        	singer = singerDao.delete(singer);
		        	// Update the message.
			        if (singer == null) {
			            messages.put("title", "Successfully deleted UserId: " + userId);
			            messages.put("disableSubmit", "true");
			        } else {
			        	messages.put("title", "Failed to delete UserId: " + userId);
			        	messages.put("disableSubmit", "false");
			        }
		        } catch (SQLException e) {
		        	if(!isDeleted) {
		        		e.printStackTrace();
						throw new IOException(e);
		        	}
		        }
	        }

	        
        }
        
        req.getRequestDispatcher("/UserDelete.jsp").forward(req, resp);
    }
}
