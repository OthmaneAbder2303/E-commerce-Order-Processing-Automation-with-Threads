package ma.ensa.ordcusthreads;

import com.google.gson.Gson;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class InventoryOrder {
    
    private int id;
    private int customerId;
    private String productDetails;
    
    public static InventoryOrder fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, InventoryOrder.class);
    }
    
}
