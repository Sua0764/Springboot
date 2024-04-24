package dw.gameshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game gameId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "point", nullable = false)
    private int point;

    @Column(name = "review_text", length = 65535)
    private String reviewText;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

}
