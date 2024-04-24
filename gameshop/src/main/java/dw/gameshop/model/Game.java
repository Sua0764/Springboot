package dw.gameshop.model;

import jakarta.persistence.*;

@Entity
@Table (name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100)
    private String name;

    @Column(length = 100)
    private String genre;

    @Column()
    private int price;

    @Column(length = 65535)
    private String image;

    @Column(length = 65535)
    private String text;

    public Game() {
    }

    public Game(long id, String name, String genre, int price, String image, String text) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.price = price;
        this.image = image;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
