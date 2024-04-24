package dw.gameshop.service;

import dw.gameshop.exception.ResourceNotFoundException;
import dw.gameshop.model.Game;
import dw.gameshop.model.User;
import dw.gameshop.repository.GameRepository;
import dw.gameshop.repository.UserRepository;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;
    @Autowired
    UserRepository userRepository;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game getGameById(long id) {
        Optional<Game> game = gameRepository.findById(id);
        if (game.isEmpty()) {
            // 예외처리
            throw new ResourceNotFoundException("Game","ID",id);
        } else {
            return game.get();
        }
    }

    public Game updateGameById(long id,Game game) {
        Optional<Game> game1 = gameRepository.findById(id);

        if (game1.isPresent()) {
            game1.get().setName(game.getName());
            game1.get().setGenre(game.getGenre());
            game1.get().setImage(game.getImage());
            game1.get().setText(game.getText());
            game1.get().setPrice(game.getPrice());

            gameRepository.save(game1.get());
            return game1.get();
        }else {
            throw new ResourceNotFoundException("Game","ID",id) ;
        }
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // 제일 비싼 게임의 정보
    public Game getGameWithMaxPrice() {
        List<Game> games = gameRepository.findAll();

        /*
        //람다식이 아닌 일반 자바코드 사용 예
        if (games.size() <= 0) {
            throw new ResourceNotFoundException("Max Price", " ", " ");
        }

        Game max = games.get(0);
        for (int i=0; i< games.size()-1; i++) {
            if (max.getPrice() > games.get(i+1).getPrice()) {
                max = games.get(i+1);
            }
        }
        return max;


        //람다식 사용 예
        return games.stream().sorted(Comparator.comparingInt((Game g)-> g.getPrice()).reversed()).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Max Price", " ", " "));*/

        //JPQL 사용 예
        return gameRepository.getNameWithMaxPrice();
    }

    // 제일 비싼게임 TOP3
    public List<Game> getGameWithMaxPriceTop3() {
        List<Game> games = gameRepository.findAll();

        /*
        games.sort(Comparator.comparingInt((Game g) -> g.getPrice()).reversed());
        List<Game> newGames = new ArrayList<>();
        newGames.add(games.get(0));
        newGames.add(games.get(1));
        newGames.add(games.get(2));
        return newGames;


        return games.stream().sorted(Comparator.comparingInt(Game::getPrice).reversed())
                .limit(3).collect(Collectors.toList());

         */
        //JPQL 사용 예
        return gameRepository.getGameWithMaxPriceTop3().stream().limit(3).collect(Collectors.toList());
    }
}