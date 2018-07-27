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

    private Boolean mTwoPane;
    private Recipe recipe ;
    private android.support.v4.app.FragmentManager mFragmentManager;
    public ArrayList<String> overviewList = new ArrayList<>();
    public List<Step> stepList;
    public List<Ingredient> ingredients;
//    public static final String OVERVIEW_LIST_EXTRA = "OVERVIEW_EXTRA";
//    public static final String RECIPE = "RECIPE";
//    public static final String STEPS_LIST = "STEP_LIST";
//    public static final String INGREDIENTS_LIST = "INGREDIENTS_LIST";
    private static final String tag = RecipeOverviewActivity.class.getSimpleName();
    Bundle args = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity_recipe_overview);
        mFragmentManager = getSupportFragmentManager();

        mTwoPane = findViewById(R.id.activity_steps_linear_layout) != null ;


        if (savedInstanceState == null){
            Intent intent = getIntent();
            if (intent != null ) {

                recipe = intent.getParcelableExtra(String.valueOf(R.string.KEY_RECIPE));
                stepList = intent.getParcelableArrayListExtra(String.valueOf(R.string.KEY_STEPS_LIST));
                ingredients = intent.getParcelableArrayListExtra(String.valueOf(R.string.KEY_INGREDIENT_LIST));
                setOverview(overviewList, stepList);
                args.putParcelableArrayList(IngredientsActivity.INGREDIENTS_EXTRA, (ArrayList<? extends Parcelable>) ingredients);
                args.putStringArrayList(String.valueOf(R.string.KEY_OVERVIEW_LIST), overviewList);
                RecipeOverviewFragment overviewFragment = new RecipeOverviewFragment();
                overviewFragment.setArguments(args);
                mFragmentManager.beginTransaction().add(R.id.recipe_overview_container, overviewFragment).commit();

                if (mTwoPane){
                    //TODO if in Tablet
                    IngredientFragment ingredientFragment = new IngredientFragment();
                    ingredientFragment.setArguments(args);
                    mFragmentManager.beginTransaction().add(R.id.ingredients_container, ingredientFragment).commit();
                }

            }
        }else{
            // TODO check the current position and populate the right layout
            Bundle args = new Bundle();
            recipe = savedInstanceState.getParcelable(String.valueOf(R.string.KEY_RECIPE));
            ingredients = savedInstanceState.getParcelableArrayList(String.valueOf(R.string.KEY_INGREDIENT_LIST));
            stepList = savedInstanceState.getParcelableArrayList(String.valueOf(R.string.KEY_STEPS_LIST));
            setOverview(overviewList, stepList);
            args.putParcelableArrayList(IngredientsActivity.INGREDIENTS_EXTRA, (ArrayList<? extends Parcelable>) ingredients);
            args.putStringArrayList(String.valueOf(R.string.KEY_OVERVIEW_LIST), overviewList);
            RecipeOverviewFragment overviewFragment = new RecipeOverviewFragment();
            overviewFragment.setArguments(args);
            mFragmentManager.beginTransaction().replace(R.id.recipe_overview_container, overviewFragment).commit();

            if (mTwoPane){
                //TODO if in Tablet

                IngredientFragment ingredientFragment = new IngredientFragment();
                ingredientFragment.setArguments(args);
                mFragmentManager.beginTransaction().replace(R.id.ingredients_container, ingredientFragment).commit();
            }
        }

        setTitle(recipe.getName());

    }

    @Override
    public void overviewItemClicked(int position){
        if (mTwoPane){

            if(position == 0){
                // display ingredient
                IngredientFragment ingredientFragment = new IngredientFragment();
                ingredientFragment.setArguments(args);
                mFragmentManager.beginTransaction().replace(R.id.ingredients_container, ingredientFragment).commit();
            }else{
                // display Clips and description
                Toast.makeText(getApplicationContext(), "mTwoPane is true", Toast.LENGTH_SHORT).show();
                PlayerFragment playerFragment = new PlayerFragment();
                args.putString(PlayerFragment.VEDIO_URL, stepList.get(position-1).getVideoURL());
                playerFragment.setArguments(args);
                mFragmentManager.beginTransaction().replace(R.id.ingredients_container, playerFragment).commit();

                DescriptionFragment descriptionFragment = new DescriptionFragment();
                args.putString(DescriptionFragment.DESCRIPTION, stepList.get(position-1).getDescription());
                args.putString(DescriptionFragment.SHORT_DESCRIPTION, stepList.get(position-1).getShortDescription());
                descriptionFragment.setArguments(args);
                mFragmentManager.beginTransaction().replace(R.id.activity_steps_instruction_container, descriptionFragment).commit();

            }
        }else { // Handle on Cell phone device

            if (position == 0) {
                Intent intentIngredients = new Intent(getApplicationContext(), IngredientsActivity.class);
                intentIngredients.putParcelableArrayListExtra(String.valueOf(R.string.KEY_INGREDIENT_LIST), (ArrayList<? extends Parcelable>) ingredients);
                startActivity(intentIngredients);
            } else {

                Intent intentSteps = new Intent(getApplicationContext(), StepsActivity.class);
                intentSteps.putExtra(PlayerFragment.VEDIO_URL, stepList.get(position - 1).getVideoURL());
                intentSteps.putExtra(DescriptionFragment.NAME, recipe.getName());
                intentSteps.putExtra(DescriptionFragment.DESCRIPTION, stepList.get(position - 1).getDescription());
                intentSteps.putExtra(DescriptionFragment.SHORT_DESCRIPTION, stepList.get(position - 1).getShortDescription());
                startActivity(intentSteps);

                /**
                Intent intentNavigate = new Intent(getApplicationContext(), NavigateActivity.class);
                intentNavigate.putExtra(NavigateActivity.POSITION, position);
                intentNavigate.putParcelableArrayListExtra(NavigateActivity.STEPS, (ArrayList<? extends Parcelable>) steps);
                startActivity(intentNavigate);
                 **/
            }
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
        outState.putParcelable(String.valueOf(R.string.KEY_RECIPE), recipe);
        outState.putParcelableArrayList(String.valueOf(R.string.KEY_STEPS_LIST), (ArrayList<? extends Parcelable>) stepList);
        outState.putParcelableArrayList(String.valueOf(R.string.KEY_INGREDIENT_LIST), (ArrayList<? extends Parcelable>) ingredients);
    }


}
