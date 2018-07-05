package ltd.scau.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Weijie Wu
 */
public class AdvancedSearchResultDto extends SearchResultDto {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    protected Date startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    protected Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "AdvancedSearchResultDto{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", key='" + key + '\'' +
                ", pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                ", users=" + users +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

}
