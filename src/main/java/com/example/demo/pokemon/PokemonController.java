package com.example.demo.pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/pokemon")
public class PokemonController {

    private final PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService){
        this.pokemonService = pokemonService;
    }

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public String getBasicPokemonData(@RequestParam String pokemonName) throws Exception {
        List<Object> pokemonData = pokemonService.getPokemonData(pokemonName, false);
        if (pokemonData == null) {
            return "{ \"error\": \"Data not found\"}";
        }
        return  "{" + pokemonData.get(0) + "}";
    }

    //Testing with POST method
    @PostMapping("/miPokemon")
    public Map<String, Object> postData(@RequestBody Map<String, Object> payload) {
        System.out.println(payload.get("name"));
        return payload;
    }

    @RequestMapping("/detailed")
    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public String getDetailedPokemonData(@RequestParam String pokemonName) throws Exception {
        List<Object> pokemonDetailedData = pokemonService.getPokemonData(pokemonName, true);
        if (pokemonDetailedData == null) {
            return "{ \"error\": \"Data not found \"}";
        }
        return "{" + pokemonDetailedData.get(0) + "," +
                     "\"description\" : \"" + pokemonDetailedData.get(1) + "\", " +
                     "\"evolutions\" :" + pokemonDetailedData.get(2) +
                "}";
    }
}
