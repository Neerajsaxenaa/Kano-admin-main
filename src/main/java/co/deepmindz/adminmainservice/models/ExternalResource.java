package co.deepmindz.adminmainservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ExternalResource {
    @Id
    private String _id;
    private String type;
    private String description;
    private String resource;
    private String maintained_by;
    private String created_at;
}
