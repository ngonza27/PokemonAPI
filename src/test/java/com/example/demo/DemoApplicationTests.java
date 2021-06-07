package com.example.demo;

import com.example.demo.pokemon.PokemonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class DemoApplicationTests {

	private final PokemonService pokemonService;

	@Autowired
	DemoApplicationTests(PokemonService pokemonService) {
		this.pokemonService = pokemonService;
	}

	@Test
	void contextLoads() throws Exception {
		Assert.isNull(pokemonService.getPokemonData("Camilo", true) , "must be null");
		Assert.notNull(pokemonService.getPokemonData("snorlax", true) , "must not be null");
	}

}
