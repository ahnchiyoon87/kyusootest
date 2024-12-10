package kyusootest.domain;

import java.time.LocalDate;
import java.util.*;
import kyusootest.infra.AbstractEvent;
import lombok.Data;

@Data
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private String productName;
    private String productId;
    private Integer qty;
}
