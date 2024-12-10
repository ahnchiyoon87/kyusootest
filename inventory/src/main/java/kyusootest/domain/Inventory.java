package kyusootest.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import kyusootest.InventoryApplication;
import kyusootest.domain.StockDecreased;
import lombok.Data;

@Entity
@Table(name = "Inventory_table")
@Data
//<<< DDD / Aggregate Root
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer stock;

    private String productName;

    @Enumerated(EnumType.STRING)
    private ProductCode productCode;

    @PostPersist
    public void onPostPersist() {
        StockDecreased stockDecreased = new StockDecreased(this);
        stockDecreased.publishAfterCommit();
    }

    public static InventoryRepository repository() {
        InventoryRepository inventoryRepository = InventoryApplication.applicationContext.getBean(
            InventoryRepository.class
        );
        return inventoryRepository;
    }

    //<<< Clean Arch / Port Method
    public static void decreaseStock(OrderPlaced orderPlaced) {
        repository()
            .findById(Long.valueOf(orderPlaced.getProductId()))
            .ifPresent(inventory -> {
                inventory.setStock(inventory.getStock() - orderPlaced.getQty());
                repository().save(inventory);
                new StockDecreased(inventory).publishAfterCommit();
            });
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
