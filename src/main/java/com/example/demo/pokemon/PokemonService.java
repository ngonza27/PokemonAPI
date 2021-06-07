package com.example.demo.pokemon;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PokemonService {

    @Cacheable("pokemon")
    public List<Object> getPokemonData(String name, boolean isDetailed) throws Exception {
        //All data acquisition can be moved into a lower level for a cleaner architecture (ex: PokemonRepository)
        String pokemonUrl = "https://pokeapi.co/api/v2/pokemon/" + name;

        JsonNode root = getRoot(pokemonUrl);
        if (root == null){
            return null;
        }

        //Gathering a pokemon's basic data
        JsonNode type = root.path("types").get(0).path("type").get("name");
        JsonNode img = root.path("sprites").get("front_default");
        JsonNode weight = root.path("weight");
        JsonNode abilities = root.path("abilities");

        String baseData = "\"img\": " + img + "," +
                          "\"type\":" + type + "," +
                          "\"weight\":" + weight + "," +
                          "\"abilities\":" + abilities;
        if(!isDetailed) {
            return Arrays.asList(baseData);
        }
        JsonNode id = root.path("id");
        //Generating response
        return Arrays.asList(baseData, getDescription(id), getEvolutions(id));
    }

    public String getDescription(JsonNode id) throws Exception {
        String descriptionUrl = "https://pokeapi.co/api/v2/characteristic/" + id;
        JsonNode root = getRoot(descriptionUrl);
        if (root == null){
            return null;
        }
        String description = root.path("descriptions").get(2).path("description").textValue(); //Getting english text
        return description;
    }

    public JsonNode getEvolutions(JsonNode id) throws Exception {
        String descriptionUrl = "https://pokeapi.co/api/v2/evolution-chain/" + id;
        JsonNode root = getRoot(descriptionUrl);
        if (root == null){
            return null;
        }
        JsonNode description = root.path("chain"); //Getting english text
        return description;
    }

    public JsonNode getRoot(String url) throws Exception {
        Logger logger = Logger.getLogger(PokemonService.class.getName());
        try {
            RestTemplate restTemplate = new RestTemplate();
            String descriptionData = restTemplate.getForObject(url, String.class); // Making PokemonAPI request
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(descriptionData);

            return root;

        } catch (final HttpClientErrorException e) {
            logger.warning("Pokemon not found");
            return null;
        }
    }
}
