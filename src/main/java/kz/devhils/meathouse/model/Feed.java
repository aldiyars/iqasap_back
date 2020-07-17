package kz.devhils.meathouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "feed")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private Users author;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    private Statuses status;


}
