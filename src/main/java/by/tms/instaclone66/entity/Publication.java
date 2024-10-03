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
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Publication {

    private Integer id;
    private User user;
    private byte[] content;
    private String description;
    private LocalDate postOfDate;
    private List<Comment> comments;
    private List<Like> likes;

    public Publication(Integer id, byte[] content, String description, LocalDate postOfDate) {
        this.id = id;
        this.content = content;
        this.description = description;
        this.postOfDate = postOfDate;
    }

    public Publication(User author, byte[] content, String description, LocalDate postOfDate) {
        this.user = author;
        this.content = content;
        this.description = description;
        this.postOfDate = postOfDate;
    }
}


