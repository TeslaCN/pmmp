package ltd.scau.dto;

/**
 * @author Weijie Wu
 */
public class RedirectResultDto extends ResultDto {

    protected String redirect;

    @Override
    public String toString() {
        return "RedirectResultDto{" +
                "redirect='" + redirect + '\'' +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

}
