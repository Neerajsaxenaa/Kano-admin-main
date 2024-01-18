package co.deepmindz.adminmainservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter@Setter
@NoArgsConstructor@Entity
public class UserBaseAvailability {
    @Id
    private String _id;
    private boolean external_availability;
    private String provided_url;
    private String created_at;

}
