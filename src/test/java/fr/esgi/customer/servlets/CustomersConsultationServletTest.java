package fr.esgi.customer.servlets;

import fr.esgi.customer.beans.Customer;
import fr.esgi.customer.services.CustomersFileParser;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static fr.esgi.customer.samples.CustomerSample.customers;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomersConsultationServletTest {

    @InjectMocks
    private CustomersConsultationServlet customersConsultationServlet;

    @Mock
    private CustomersFileParser customersFileParser;

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Test
    void shouldDoGet() throws ServletException, IOException {
        List<Customer> customers = customers();

        when(customersFileParser.parse("customers.csv")).thenReturn(customers);
        when(httpServletRequest.getRequestDispatcher("/customers.jsp")).thenReturn(requestDispatcher);

        customersConsultationServlet.doGet(httpServletRequest, httpServletResponse);

        verify(customersFileParser).parse("customers.csv");
        verify(httpServletRequest).setAttribute(Customer.class.getName(), customers);
        verify(httpServletRequest).getRequestDispatcher("/customers.jsp");
        verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);

        verifyNoMoreInteractions(customersFileParser, httpServletRequest, requestDispatcher);
    }

}
