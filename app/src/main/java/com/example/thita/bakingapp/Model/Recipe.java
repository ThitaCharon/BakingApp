package com.example.thita.bakingapp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("ingredients")
    private List<Ingredient> ingredientsList = new ArrayList<>();

    @SerializedName("steps")
    private List<Step> stepsList = new ArrayList<>();
    @SerializedName("servings")
    private int servings;
    @SerializedName("image")
    private String image;

    //Constructor
    public Recipe(){}

    //Getter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredientsList() {
        return ingredientsList;
    }

    public List<Step> getStepsList() {
        return stepsList;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }


    //setter
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredientsList(List<Ingredient> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public void setStepsList(List<Step> stepsList) {
        this.stepsList = stepsList;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
