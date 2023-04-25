package fr.esgi.customer.servlets;

import fr.esgi.customer.beans.Customer;
import fr.esgi.customer.services.CustomersFileParser;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/list")
public class CustomersConsultationServlet extends HttpServlet {

    private final CustomersFileParser customersFileParser;

    @Inject
    public CustomersConsultationServlet(CustomersFileParser customersFileParser) {
        this.customersFileParser = customersFileParser;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customers = customersFileParser.parse("customers.csv");

        req.setAttribute(Customer.class.getName(), customers);

        req.getRequestDispatcher("/customers.jsp").forward(req, resp);
    }
}
