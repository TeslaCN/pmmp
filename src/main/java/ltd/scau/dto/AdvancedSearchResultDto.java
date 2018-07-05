package ltd.scau.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Weijie Wu
 */
public class AdvancedSearchResultDto extends SearchResultDto {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    protected Date start;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    protected Date end;

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "AdvancedSearchResultDto{" +
                "start=" + start +
                ", end=" + end +
                ", key='" + key + '\'' +
                ", pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                ", users=" + users +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

}
