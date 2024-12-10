package kyusootest.domain;

import java.time.LocalDate;
import java.util.*;
import kyusootest.infra.AbstractEvent;
import lombok.Data;

@Data
public class StockDecreased extends AbstractEvent {

    private Long id;
    private Integer stock;
    private String productName;
    private Long orderId;
}
