package com.example.demo.pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/pokemon")
public class PokemonController {

    private final PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService){
        this.pokemonService = pokemonService;
    }

    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public String getBasicPokemonData(@RequestParam String pokemonName) throws Exception {
        List<Object> pokemonData = pokemonService.getPokemonData(pokemonName, false);
        if (pokemonData == null) {
            return "{ \"error\": \"Data not found\"}";
        }
        return  "{" + pokemonData.get(0) + "}";
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
