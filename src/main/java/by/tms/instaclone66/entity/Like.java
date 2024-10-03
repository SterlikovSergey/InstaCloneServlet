package by.tms.instaclone66.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Like {
    private Integer id;
    private User authorDto;
    private LocalDate dateOfLike;
}
