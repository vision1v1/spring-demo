package springbootmvc.filters;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String filterName = this.getFilterName();
        System.out.println("filterName: "+ filterName + "---- request : " + request + " response : " + response);

        filterChain.doFilter(request,response);
        System.out.println("TestFilter ---------------");
    }
}
