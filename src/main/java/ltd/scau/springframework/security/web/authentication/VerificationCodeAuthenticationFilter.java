package ltd.scau.springframework.security.web.authentication;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Weijie Wu
 */
public class VerificationCodeAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Log logger = LogFactory.getLog(VerificationCodeAuthenticationFilter.class);

    public VerificationCodeAuthenticationFilter() {
        super();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String verificationCode = (String) request.getSession().getAttribute("VERIFICATION_CODE");
        String inputCode = request.getParameter("verify");

        logger.info("SESSION: " + verificationCode + ", INPUT: " + inputCode);

        if (inputCode != null && !inputCode.trim().equals("") && verificationCode != null && !verificationCode.trim().equals("") && inputCode.equalsIgnoreCase(verificationCode)) {
            return super.attemptAuthentication(request, response);
        }
        throw new BadCredentialsException("验证码不一致");
    }
}
