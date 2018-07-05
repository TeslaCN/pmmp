package ltd.scau.springframework.controller;

import ltd.scau.util.Constant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author Weijie Wu
 */
@RestController
@RequestMapping("/util")
public class UtilController {

    private static final Log logger = LogFactory.getLog(UserController.class);

    @RequestMapping(value = "/code", produces = "image/jpeg")
    public void getCode(HttpSession session, HttpServletResponse response) {
        int width = 120;
        int height = 40;
        int codeCount = 4;
        int x = 0;
        int fontHeight;
        int codeY;
        char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        x = width / (codeCount + 1);
        fontHeight = height - 2;
        codeY = height - 4;
        BufferedImage buffImg = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();

        Random random = new Random();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
        g.setFont(font);

        g.setColor(Color.BLACK);
        int lines = 20;
        for (int i = 0; i < lines; i++) {
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x2, y2, x + xl, y2 + yl);
        }

        StringBuffer randomCode = new StringBuffer();

        int red = 0, green = 0, blue = 0;

        for (int i = 0; i < codeCount; i++) {
            String strRand = String.valueOf(codeSequence[random.nextInt(36)]);
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawString(strRand, (i + 1) * x, codeY);
            randomCode.append(strRand);
        }
        session.setAttribute(Constant.VERIFICATION_CODE, randomCode.toString());
        ServletOutputStream sos;
        try {
            sos = response.getOutputStream();
            ImageIO.write(buffImg, "jpeg", sos);
            sos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
