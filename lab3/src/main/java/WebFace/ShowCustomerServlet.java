package WebFace;

import BusinessLogicModels.Customer;
import BusinessLogicModels.ICustomerService;
import DI.DIContainer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public class ShowCustomerServlet extends HttpServlet {
    private ICustomerService customerService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String customerNumber = req.getParameter("customerNumber");
        if(!customerNumber.isEmpty()) {
            customerService = DIContainer.getInstance().createService();
            try {
                Customer customer = customerService.findByID(Integer.parseInt(customerNumber));
                if (customer != null) {
                    req.setAttribute("customerNumber", customerNumber);
                    req.setAttribute("customerName", customer.getName());
                    req.setAttribute("customerZipCode", customer.getZip());
                    req.setAttribute("customerCity", customer.getCity());
                }
            } catch (final NumberFormatException e) {
            }
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/showCustomer.jsp");
        requestDispatcher.forward(req, resp);
    }
}
