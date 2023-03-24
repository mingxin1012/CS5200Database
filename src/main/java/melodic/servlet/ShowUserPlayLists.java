package melodic.servlet;

import melodic.dal.*;
import melodic.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/showuserplaylists")
public class ShowUserPlayLists extends HttpServlet {
	
	protected PlayListDao playListDao;
	
	@Override
	public void init() throws ServletException {
		playListDao = PlayListDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
		// Retrieve and validate UserName.
        String userId = req.getParameter("userid");
        if (userId == null || userId.trim().isEmpty()) {
            messages.put("title", "Invalid userid.");
        } else {
        	messages.put("title", "PlayList for " + userId);
        }
        
        // Retrieve BlogUsers, and store in the request.
        List<PlayList> playLists;
        try {
        	User user = new User(Integer.valueOf(userId));
        	playLists = playListDao.getPLayListForUser(user);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("playlists", playLists);
        req.getRequestDispatcher("/ShowUserPlayLists.jsp").forward(req, resp);
	}
}
