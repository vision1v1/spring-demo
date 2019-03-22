package springbootmvc.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyHandlerInterceptor implements HandlerInterceptor {

    // preHandle（..）方法返回一个布尔值。 您可以使用此方法来中断或继续执行链的处理。
    // 当此方法返回true时，处理程序执行链继续。
    // 当它返回false时，DispatcherServlet假定拦截器本身已处理请求（例如，呈现适当的视图）并且不继续执行执行链中的其他拦截器和实际处理程序。
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return true;
    }

    // 请注意，对于在HandlerAdapter中和在postHandle之前编写和提交响应的@ResponseBody和ResponseEntity方法，postHandle不太有用。
    // 这意味着对响应进行任何更改都为时已晚，例如添加额外的标头。
    // 对于此类方案，您可以实现ResponseBodyAdvice并将其声明为Controller Advice bean或直接在RequestMappingHandlerAdapter上进行配置。
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
