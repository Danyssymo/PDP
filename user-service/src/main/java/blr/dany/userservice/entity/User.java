package blr.dany.userservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;
    @Column(name = "telegram_name")
    private String telegramName;
    @Column(name = "is_sub")
    private Boolean isSub;
    @Column(name = "country")
    private String country;
    @Column(name = "chat_id")
    private String chatId;
}
