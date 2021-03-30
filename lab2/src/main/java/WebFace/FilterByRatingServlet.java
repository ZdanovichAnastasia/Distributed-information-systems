package WebFace;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FilterByRatingServlet extends HttpServlet{
    private String wrongRating = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("WrongRating", wrongRating);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("filterByRating.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("filter")!=null) {
            String rating = req.getParameter("rating");
            try{
                if(!rating.isEmpty() && Double.parseDouble(rating) >= 0 && Double.parseDouble(rating) < 11) {
                    wrongRating = "";
                    HttpSession session = req.getSession();
                    session.setAttribute("ratingForFilter", rating);
                    resp.sendRedirect(req.getContextPath() + "/showFilteredSerials");
                } else {
                    wrongRating = "Incorrect input: rating must be from 0 to 10";
                }
            }catch (final NumberFormatException e) {
                wrongRating = "Incorrect input: rating must be from 0 to 10";
            }
        }
    }
}
