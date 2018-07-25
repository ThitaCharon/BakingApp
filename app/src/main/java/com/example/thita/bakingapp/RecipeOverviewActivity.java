package com.example.thita.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.thita.bakingapp.Model.Ingredient;
import com.example.thita.bakingapp.Model.Recipe;
import com.example.thita.bakingapp.Model.Step;

import java.util.ArrayList;
import java.util.List;

public class RecipeOverviewActivity extends AppCompatActivity implements RecipeOverviewFragment.OverviewFragListerner {

    private Recipe recipe ;
    private android.support.v4.app.FragmentManager mFragmentManager;
    public ArrayList<String> overviewList = new ArrayList<>();
    public List<Step> stepsList;
    public List<Ingredient> ingredientsList;
    public static final String OVERVIEW_LIST_EXTRA = "OVERVIEW_EXTRA";
    public static final String RECIPE = "RECIPE";
    public static final String STEPS_LIST = "STEP_LIST";
    public static final String INGREDIENTS_LIST = "INGREDIENTS_LIST";
    private static final String tag = RecipeOverviewActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity_recipe_overview);

        if (savedInstanceState == null){

            Intent intent = getIntent();
            if (intent != null ) {

                recipe = intent.getParcelableExtra(RecipeActivity.RECIPE_EXTRA);
                stepsList = intent.getParcelableArrayListExtra(RecipeActivity.STEP_LIST_EXTRA);
                ingredientsList = intent.getParcelableArrayListExtra(RecipeActivity.INGREDIENT_LIST_EXTRA);
                setOverview(overviewList, stepsList);
                Bundle args = new Bundle();
                args.putStringArrayList(OVERVIEW_LIST_EXTRA, overviewList);
                RecipeOverviewFragment overviewFragment = new RecipeOverviewFragment();
                overviewFragment.setArguments(args);
                mFragmentManager = getSupportFragmentManager();
                mFragmentManager.beginTransaction().add(R.id.recipe_overview_container, overviewFragment).commit();

            }
        }else{
            recipe = savedInstanceState.getParcelable(RECIPE);
            ingredientsList = savedInstanceState.getParcelableArrayList(INGREDIENTS_LIST);
            stepsList = savedInstanceState.getParcelableArrayList(STEPS_LIST);
            setOverview(overviewList, stepsList);
            Bundle args = new Bundle();
            args.putStringArrayList(OVERVIEW_LIST_EXTRA, overviewList);
            RecipeOverviewFragment overviewFragment = new RecipeOverviewFragment();
            overviewFragment.setArguments(args);
            mFragmentManager = getSupportFragmentManager();
            mFragmentManager.beginTransaction().replace(R.id.recipe_overview_container, overviewFragment).commit();
        }
        setTitle(recipe.getName());

    }


    @Override
    public void overviewItemClicked(int position){
        // tracking on position 0 is Ingredients others index are represented each step
        if (position == 0){
            Toast.makeText(getApplicationContext(), "Open IngredientsActivity" , Toast.LENGTH_SHORT).show();
            Intent intentIngredients = new Intent(getApplicationContext(), IngredientsActivity.class);
            intentIngredients.putParcelableArrayListExtra(INGREDIENTS_LIST, (ArrayList<? extends Parcelable>) ingredientsList);
            startActivity(intentIngredients);
        }else {
            Toast.makeText(getApplicationContext(), "Open StepsActivity" , Toast.LENGTH_SHORT).show();
            Intent intentSteps = new Intent(getApplicationContext(), StepsActivity.class);
            intentSteps.putExtra(StepsActivity.POSITION, position);
            intentSteps.putExtra(RECIPE,recipe);
            intentSteps.putParcelableArrayListExtra(STEPS_LIST, (ArrayList<? extends Parcelable>) stepsList);
            startActivity(intentSteps);
        }

    }

    private void setOverview(ArrayList<String> overviewList, List<Step> stepsList) {
        overviewList.add(this.recipe.getName() + " Ingredients");
        for (int i = 0; i < stepsList.size(); i++) {
            overviewList.add(stepsList.get(i).getShortDescription());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(RECIPE, recipe);
        outState.putParcelableArrayList(STEPS_LIST, (ArrayList<? extends Parcelable>) stepsList);
        outState.putParcelableArrayList(INGREDIENTS_LIST, (ArrayList<? extends Parcelable>) ingredientsList);
    }


}
