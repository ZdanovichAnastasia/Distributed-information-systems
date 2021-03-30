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
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class EditServlet extends HttpServlet {
    @EJB private ISerialServices serialServices;
    private String wrongRating = "";
    private String wrongCount = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path path = Paths.get(getServletContext().getRealPath("WEB-INF/classes/serials.xml"));
        serialServices = DIContainer.getInstance().createBean(path);
        List<String> SerialsNamesList = serialServices.getSerialsNames();
        req.setAttribute("SerialsNamesList", SerialsNamesList);
        req.setAttribute("wrongRating", wrongRating);
        req.setAttribute("wrongCount", wrongCount);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("edit.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("editS")!=null){
            Serial serial = getValues(req, resp);
            if(serial.getRating() == -1 || serial.getCount_episodes()<1){
                DIContainer diContainer = DIContainer.getInstance();
                Path path = Paths.get(getServletContext().getRealPath("WEB-INF/classes/serials.xml"));
                serialServices = diContainer.createBean(path);
                serialServices.editSerial(req.getParameter("nameS"), serial);
            }
        }
    }

    private Serial getValues(HttpServletRequest req, HttpServletResponse resp){
        Serial serial = new Serial();
        serial.setName(req.getParameter("name"));
        String rating = req.getParameter("rating");
        String count = req.getParameter("count");
        if(!rating.isEmpty()) {
            if(Double.parseDouble(rating) < 0 || Double.parseDouble(rating) > 10) {
                wrongRating = "Incorrect input: rating must be from 0 to 10";
                serial.setRating(-1);
            }
            else {
                wrongRating = "";
                serial.setRating(Double.parseDouble(req.getParameter("rating")));
            }
        }
        else
        {
            wrongRating = "";
            serial.setRating(-1);
        }
        if(!count.isEmpty()) {
            if(Integer.parseInt(count) < 1) {
                wrongCount = "Incorrect input: the count of episodes cannot be less than 1";
            }
            else {
                wrongCount = "";
                serial.setCount_episodes(Integer.parseInt(req.getParameter("count")));
            }
        }
        else
        {
            wrongCount = "";
        }
        return serial;
    }

}
