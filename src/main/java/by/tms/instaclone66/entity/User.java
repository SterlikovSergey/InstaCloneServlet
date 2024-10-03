package by.tms.instaclone66.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    // Геттеры и сеттеры для всех полей
    private Integer id;
    private String username;
    private String email;
    private String password;
    private byte[] avatar;
    private String bio;
    private LocalDate registrationOfDate;
    private List<Publication> posts;

    public User(Integer id, String username, String email, byte[] avatar, String bio, LocalDate registrationOfDate) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.avatar = avatar;
        this.bio = bio;
        this.registrationOfDate = registrationOfDate;
    }

    public User(String username, String email, String password, byte[] avatar, String bio, LocalDate registrationOfDate) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.bio = bio;
        this.registrationOfDate = registrationOfDate;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
