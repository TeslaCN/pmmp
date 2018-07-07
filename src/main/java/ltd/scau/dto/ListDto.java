package ltd.scau.dto;

import javax.validation.constraints.Min;

/**
 * @author Weijie Wu
 */
public class ListDto extends ResultDto {

    @Min(1)
    protected int pageSize;

    @Min(1)
    protected int pageNo;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public String toString() {
        return "ListDto{" +
                "pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
