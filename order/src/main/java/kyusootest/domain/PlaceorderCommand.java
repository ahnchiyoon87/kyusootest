package kyusootest.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class PlaceorderCommand {

    private Long id;
    private String productName;
    private String productId;
    private Integer qty;
}
