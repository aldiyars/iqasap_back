package kz.devhils.meathouse.model.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_profile")
@Entity
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tel")
    private int tel;

    @Column(name = "address")
    private String address;

    @Column(name = "image_url")
    private String imgUrl;

    @Column(name = "lat")
    private String lat;

    @Column(name = "lng")
    private String lng;
}
