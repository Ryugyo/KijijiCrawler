package c01a3.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleCORSFilter implements Filter {
	private boolean isCross = false;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (isCross) {
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
			httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
			httpServletResponse.setHeader("Access-Control-Max-Age", "0");
			httpServletResponse.setHeader("Access-Control-Allow-Headers",
					"Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
			httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
			httpServletResponse.setHeader("XDomainRequestAllowed", "1");
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		String isCrossStr = filterConfig.getInitParameter("IsCross");
		isCross = isCrossStr.equals("true") ? true : false;
	}

	public void destroy() {
		isCross = false;
	}
}