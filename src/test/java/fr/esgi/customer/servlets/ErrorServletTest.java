package fr.esgi.customer.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ErrorServletTest {

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Test
    void shouldDoGet() throws ServletException, IOException {
        when(httpServletRequest.getRequestDispatcher("/error.jsp")).thenReturn(requestDispatcher);

        ErrorServlet errorServlet = new ErrorServlet();

        errorServlet.doGet(httpServletRequest, httpServletResponse);

        verify(httpServletRequest).getRequestDispatcher("/error.jsp");
        verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);

        verifyNoMoreInteractions(httpServletRequest, requestDispatcher);
    }

}
