package ltd.scau.dto;

/**
 * @author Weijie Wu
 */
public class SearchResultDto extends UserListDto {

    protected String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "SearchResultDto{" +
                "key='" + key + '\'' +
                ", users=" + users +
                ", pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
