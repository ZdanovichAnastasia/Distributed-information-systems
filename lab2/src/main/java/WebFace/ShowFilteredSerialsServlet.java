package WebFace;

import BusinessLogicModels.ISerialServices;
import BusinessLogicModels.Serial;
import DI.DIContainer;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ShowFilteredSerialsServlet extends HttpServlet {
    @EJB private ISerialServices serialServices;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DIContainer diContainer = DIContainer.getInstance();
        Path path = Paths.get(getServletContext().getRealPath("WEB-INF/classes/serials.xml"));
        serialServices = diContainer.createBean(path);

        HttpSession session = req.getSession();
        double rating = Double.parseDouble(session.getAttribute("ratingForFilter").toString());

        List<Serial> SerialsList = serialServices.filterSerialByRating(rating);
        req.setAttribute("SerialsList", SerialsList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("showFilteredSerials.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
