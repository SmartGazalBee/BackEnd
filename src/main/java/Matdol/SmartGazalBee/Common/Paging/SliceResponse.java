package Matdol.SmartGazalBee.Common.Paging;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SliceResponse<T> {
    @JsonProperty("content")
    private List<T> content;
    private Boolean hasNext;
}
