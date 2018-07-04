package ltd.scau.mybatis.po;

public class Role {

    public static final String ROLE_USER = "ROLE_USER";

    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    private Long id;

    private String rolename;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }
}