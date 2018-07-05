package ltd.scau.springframework.security.web.authentication;

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
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String verificationCode = (String) request.getSession().getAttribute("VERIFICATION_CODE");
        String inputCode = request.getParameter("verify");
        if (inputCode != null && !inputCode.trim().equals("") && verificationCode != null && !verificationCode.trim().equals("") && inputCode.equalsIgnoreCase(verificationCode)) {
            return super.attemptAuthentication(request, response);
        }
        throw new BadCredentialsException("验证码不一致");
    }
}
