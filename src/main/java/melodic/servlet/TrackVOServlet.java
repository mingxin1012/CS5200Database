package melodic.servlet;

import melodic.dal.*;
import melodic.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/trackvo")
public class TrackVOServlet extends HttpServlet {
  protected TrackVODao trackVODao;

  @Override
  public void init() throws ServletException{
    trackVODao = TrackVODao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException{
    List<TrackVO> trackVOList;
    try{
      trackVOList = trackVODao.getTopTen();
    } catch (SQLException e){
      e.printStackTrace();
      throw new IOException();
    }

    request.setAttribute("trackVOList", trackVOList);

    request.getRequestDispatcher("/TrackVO.jsp").forward(request, response);
  }


}
