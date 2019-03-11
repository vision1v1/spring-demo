package springbootminisci;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public interface MiniWebApplicationInitializer {
    void onStartup(ServletContext servletContext) throws ServletException;
}
