package fr.esgi.customer.servlets;

import fr.esgi.customer.beans.Customer;
import fr.esgi.customer.exception.MissingCustomerIdException;
import fr.esgi.customer.exception.NotFoundCustomerException;
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

import static fr.esgi.customer.samples.CustomerSample.customers;
import static fr.esgi.customer.samples.CustomerSample.oneCustomer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerDetailConsultationServletTest {

    private static final String FILENAME_TO_PARSE = "customers.csv";

    @InjectMocks
    private CustomerDetailConsultationServlet customerDetailConsultationServlet;

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
        String jspPath = "/customerDetail.jsp";
        Customer customer = oneCustomer();

        when(httpServletRequest.getParameter("id")).thenReturn("46890");
        when(customersFileParser.parse(FILENAME_TO_PARSE)).thenReturn(customers());
        when(httpServletRequest.getRequestDispatcher(jspPath)).thenReturn(requestDispatcher);

        customerDetailConsultationServlet.doGet(httpServletRequest, httpServletResponse);

        verify(httpServletRequest).getParameter("id");
        verify(customersFileParser).parse(FILENAME_TO_PARSE);
        verify(httpServletRequest).setAttribute(Customer.class.getName(), customer);
        verify(httpServletRequest).getRequestDispatcher(jspPath);
        verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);

        verifyNoMoreInteractions(httpServletRequest, httpServletResponse, customersFileParser, requestDispatcher);
    }

    @Test
    void givenNullIdParamValue_shouldThrowMissingCustomerIdException() {
        assertThatExceptionOfType(MissingCustomerIdException.class)
                .isThrownBy(() -> customerDetailConsultationServlet.doGet(httpServletRequest, httpServletResponse))
                .satisfies(e -> assertThat(e.getMessage()).isEqualTo("Parameter id is null"));

        verify(httpServletRequest).getParameter("id");

        verifyNoInteractions(httpServletResponse, customersFileParser, requestDispatcher);
        verifyNoMoreInteractions(httpServletRequest);
    }

    @Test
    void givenNonExistentIdParamValue_shouldThrowNotFoundCustomerException() {
        when(httpServletRequest.getParameter("id")).thenReturn("46390");
        when(customersFileParser.parse(FILENAME_TO_PARSE)).thenReturn(customers());

        assertThatExceptionOfType(NotFoundCustomerException.class)
                .isThrownBy(() -> customerDetailConsultationServlet.doGet(httpServletRequest, httpServletResponse))
                .satisfies(e -> assertThat(e.getMessage()).isEqualTo("Not found referenceId : 46390"));

        verify(httpServletRequest).getParameter("id");
        verify(customersFileParser).parse(FILENAME_TO_PARSE);

        verifyNoInteractions(httpServletResponse, requestDispatcher);
        verifyNoMoreInteractions(httpServletRequest, customersFileParser);
    }

}
