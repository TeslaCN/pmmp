package ltd.scau.springframework.security.web.authentication;

import ltd.scau.util.Constant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Weijie Wu
 */
public class CustomAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    private static final Log logger = LogFactory.getLog(CustomAuthenticationEntryPoint.class);

    /**
     * @param loginFormUrl URL where the login page can be found. Should either be
     *                     relative to the web-app context path (include a leading {@code /}) or an absolute
     *                     URL.
     */
    public CustomAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    @Override
    protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        logger.info(uri);
        session.setAttribute(Constant.SESSION_REDIRECT, uri);
        return super.determineUrlToUseForThisRequest(request, response, exception);
    }
}
