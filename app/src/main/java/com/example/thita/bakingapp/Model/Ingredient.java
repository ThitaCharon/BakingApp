package com.example.thita.bakingapp.Model;

public class Ingredient {

    private double quantity;
    private String measure;
    private String ingredient;

    public Ingredient() { }

    //Getter
    public double getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    //Setter
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
