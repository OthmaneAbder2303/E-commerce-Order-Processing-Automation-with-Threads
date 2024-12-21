package ma.ensa.ordcusthreads;

import com.google.gson.Gson;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Customer {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;

    public static Customer fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Customer.class);
    }
}
