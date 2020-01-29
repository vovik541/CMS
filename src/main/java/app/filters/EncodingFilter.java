package app.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    /**
     * LOG for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(EncodingFilter.class);

    private String encoding;

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String requestEncoding = request.getCharacterEncoding();
        if (requestEncoding == null) {
            LOGGER.trace("Request encoding = null, set encoding ---> " + encoding);
            request.setCharacterEncoding(encoding);
        }
        chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        encoding = fConfig.getInitParameter("encoding");
        LOGGER.trace("Encoding from web.xml ---> " + encoding);
    }

    public void destroy() {
    }
}
