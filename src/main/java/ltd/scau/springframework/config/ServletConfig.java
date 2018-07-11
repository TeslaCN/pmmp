package ltd.scau.springframework.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.io.IOException;
import java.util.Properties;

/**
 * web.xml is unnecessary
 *
 * @author Weijie Wu
 */
public class ServletConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final Log logger = LogFactory.getLog(ServletConfig.class);

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String uploadPath = getClass().getResource("/../..").getFile();
        uploadPath = uploadPath.substring(0, uploadPath.length() - 1);
        uploadPath += properties.getProperty("upload.path");
        logger.info("Upload path: " + uploadPath);
        Long singleMaxSize = (long) (8 * Math.pow(2, 20));
        Long totalMaxSize = (long) (64 * Math.pow(2, 20));
        registration.setMultipartConfig(new MultipartConfigElement(uploadPath, singleMaxSize, totalMaxSize, 0));
    }
}
