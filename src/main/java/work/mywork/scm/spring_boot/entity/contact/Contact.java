package work.mywork.scm.spring_boot.entity.contact;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import work.mywork.scm.spring_boot.entity.User;

@Entity
@Table(name="user_contacts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    @Id
    private String id;

    private String Name;
    private String email;
    private String phoneNumber;
    private String address;
    private String picture;
    @Column(length=1000)
    private String description;
    private boolean favorite = false;
    private String websiteLink;
    private String linkedInLink;
    private String instagramLink;
    private String cloudinaryImagePublicId;

    @ManyToOne
    private User user;
}
