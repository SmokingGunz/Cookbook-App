package com.coderscampus.cookbook.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.coderscampus.cookbook.domain.Recipe;

@Service
public class FileService {

	public List<Recipe> allRecipes() throws IOException {

		List<Recipe> recipes = new ArrayList<Recipe>(100);

		CSVFormat format = CSVFormat.DEFAULT.withDelimiter(',') // Specify the delimiter character (e.g., comma)
											.withEscape('\\') // Specify the escape character (e.g., backslash)
											.withHeader("Cooking Minutes", "Dairy Free", "Gluten Free", "Instructions", "Preparation Minutes", "Price Per Serving", "Ready In Minutes", "Servings", "Spoonacular Score", "Title", "Vegan", "Vegetarian") // Specify that the file contains a header
											.withSkipHeaderRecord()
											.withIgnoreSurroundingSpaces();

	
		try(Reader in = new FileReader("recipes.txt")) {
			
			Iterable<CSVRecord> records = format.parse(in);
			for (CSVRecord record : records) {
				Integer cookingMinutes = Integer.parseInt(record.get("Cooking Minutes"));

				Boolean dairyFree = Boolean.parseBoolean(record.get("Dairy Free"));

				Boolean glutenFree = Boolean.parseBoolean(record.get("Gluten Free"));

				String instructions = String.valueOf(record.get("Instructions"));

				Double preparationMinutes = Double.parseDouble(record.get("Preparation Minutes"));

				Double pricePerServing = Double.parseDouble(record.get("Price Per Serving"));

				Integer readyInMinutes = Integer.parseInt(record.get("Ready In Minutes"));

				Integer servings = Integer.parseInt(record.get("Servings"));

				Double spoonacularScore = Double.parseDouble(record.get("Spoonacular Score"));

				String title = String.valueOf(record.get("Title"));

				Boolean vegan = Boolean.parseBoolean(record.get("Vegan"));

				Boolean vegetarian = Boolean.parseBoolean(record.get("Vegetarian"));

				Recipe recipe = new Recipe();

				recipe.setCookingMinutes(cookingMinutes);

				recipe.setDairyFree(dairyFree);

				recipe.setGlutenFree(glutenFree);

				recipe.setInstructions(instructions);

				recipe.setPreparationMinutes(preparationMinutes);

				recipe.setPricePerServing(pricePerServing);

				recipe.setReadyInMinutes(readyInMinutes);

				recipe.setServings(servings);

				recipe.setSpoonacularScore(spoonacularScore);

				recipe.setTitle(title);

				recipe.setVegan(vegan);

				recipe.setVegetarian(vegetarian);

				recipes.add(recipe);

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recipes;

	}

	public List<Recipe> getVeganRecipes() {

		List<Recipe> veganRecipes = null;
		try {
			veganRecipes = allRecipes().stream()
									   .filter(Recipe::getVegan)
									   .collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return veganRecipes;
	}
	public List<Recipe> getGlutenFreeRecipes() {
		 List<Recipe> glutenFreeRecipes = null;
		 
		 try {
            glutenFreeRecipes = allRecipes().stream()
                                      .filter(Recipe::getGlutenFree)
                                      .collect(Collectors.toList());
        } catch (IOException e) {
        	e.printStackTrace();
        }
		 
		return glutenFreeRecipes;
	}
	
	public List<Recipe> getVeganAndGlutenFreeRecipes() {
		
		List<Recipe> veganAndGlutenFreeRecipes = null;
		
		try {
			veganAndGlutenFreeRecipes= allRecipes().stream()
												   .filter(recipe -> recipe.getGlutenFree() && recipe.getVegan())
												   .collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return veganAndGlutenFreeRecipes;
	}
	
	public List<Recipe> getVegetarianRecipes() {
		
		List<Recipe> vegetarianRecipes = null;
        
        try {
            vegetarianRecipes = allRecipes().stream()
                                            .filter(Recipe::getVegetarian)
                                            .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return vegetarianRecipes;
        
	}

}
