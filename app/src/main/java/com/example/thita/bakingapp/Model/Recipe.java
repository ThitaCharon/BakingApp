package com.example.thita.bakingapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Parcelable {
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


    protected Recipe(Parcel in) {
        id = in.readInt();
        name = in.readString();
        ingredientsList = in.createTypedArrayList(Ingredient.CREATOR);
        stepsList = in.createTypedArrayList(Step.CREATOR);
        servings = in.readInt();
        image = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

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

    public ArrayList<String> getRecipeOverview(){
        ArrayList<String> overview = new ArrayList<>();
        overview.add(name + " Ingredients");
        for (int i = 0; i < this.stepsList.size(); i++){
            overview.add(stepsList.get(i).getShortDescription());
        }
        return overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeList(ingredientsList);
        dest.writeList(stepsList);
        dest.writeInt(servings);
        dest.writeString(image);
    }
}
