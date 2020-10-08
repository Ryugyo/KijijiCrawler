package c01a3.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class EncodingFilter implements Filter {
	private FilterConfig config;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String encoding = config.getInitParameter("encoding");
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		HttpServletRequest req = (HttpServletRequest)request;
		if("GET".equals(req.getMethod().toUpperCase())){
			MyHttpServletRequest temp = new MyHttpServletRequest(req);
			temp.setEncoding(encoding);
			request=temp;
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {		
		this.config = config;
	}
	
	public void destroy() {
	}
}