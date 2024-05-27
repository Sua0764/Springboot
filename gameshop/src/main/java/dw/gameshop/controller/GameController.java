package dw.gameshop.controller;

import dw.gameshop.model.Game;
import dw.gameshop.model.User;
import dw.gameshop.service.GameService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class GameController {

    GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Game>> getAllGames() {
        return new ResponseEntity<>(gameService.getAllGames(), HttpStatus.OK) ;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable long id) {
        return new ResponseEntity<>(gameService.getGameById(id), HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Game> updateGameById(@PathVariable long id, @RequestBody Game game) {
        return new ResponseEntity<>(gameService.updateGameById(id,game), HttpStatus.OK);
    }

    @PostMapping("/products/user")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(gameService.saveUser(user),HttpStatus.OK);
    }

    @GetMapping("products/maxprice")
    public ResponseEntity<Game> getGameWithMaxPrice() {
        return new ResponseEntity<>(gameService.getGameWithMaxPrice(),HttpStatus.OK);
    }

    @GetMapping("/products/maxpricetop3")
    public ResponseEntity<List<Game>> getGameWithMaxPriceTop3() {
        return new  ResponseEntity<>(gameService.getGameWithMaxPriceTop3(),HttpStatus.OK);
    }
}
