package test;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.android.yet_another_recipe_app.objects.Ingredient;
import com.android.yet_another_recipe_app.objects.Measurement;
import com.android.yet_another_recipe_app.objects.Recipe;
import com.android.yet_another_recipe_app.objects.RecipeIngredients;
import com.android.yet_another_recipe_app.objects.RecipeType;
import com.android.yet_another_recipe_app.objects.Steps;

import java.util.ArrayList;

import database.RecipeDatabaseHelper;

public class RecipeDatabaseHelperTest  extends AndroidTestCase {
        private RecipeDatabaseHelper db;

        public void setUp(){
            RenamingDelegatingContext context
                    = new RenamingDelegatingContext(getContext(), "test_");
            db = new RecipeDatabaseHelper(context);
        }

        public void testAddIngredient(){
            Ingredient i = new Ingredient(-1,"foo","bar");
            assertEquals(1, db.insertIngredient(i));
        }
        public void testUpdateIngredient()
        {
            Ingredient i = new Ingredient(1,"foo","bar");
            db.insertIngredient(i);
            i.set_description("pizza");
            db.updateIngredient(i);
            Ingredient j = db.selectIngredient(1);
            assertEquals("pizza",j.get_description());
        }
        public void testDeleteIngredient()
        {
            Ingredient i = new Ingredient(1,"foo","bar");
            db.insertIngredient(i);
            db.deleteIngredient(i.get_ingredientID());
            Ingredient j = db.selectIngredient(1);
            assertEquals(null,j);

        }
        public void testSelectIngredient()
        {
            Ingredient i = new Ingredient(1,"foo","bar");
            db.insertIngredient(i);
            Ingredient c= new Ingredient(2,"foo","bar");
            db.insertIngredient(c);

            Ingredient j = db.selectIngredient(2);
            assertEquals(c.get_ingredientID(),j.get_ingredientID());
        }

    public void testAdMeasurement(){
        Measurement i = new Measurement(-1,"foo");
        assertEquals(1, db.insertMeasurement(i));
    }
    public void testUpdateMeasurement()
    {
        Measurement i = new Measurement(1,"bar");
        db.insertMeasurement(i);
        i.set_description("pizza");
        db.updateMeasurement(i);
        Measurement j = db.selectMeasurement(1);
        assertEquals("pizza",j.get_description());
    }
    public void testDeleteMeasurement()
    {
        Measurement i = new Measurement(1,"foo");
        db.insertMeasurement(i);
        db.deleteMeasurement(i.get_measurementID());

        Measurement j = db.selectMeasurement(1);
        assertEquals(null,j);
    }
    public void testSelectMeasurement()
    {
        Measurement i = new Measurement(1,"foo");
        db.insertMeasurement(i);
        Measurement c= new Measurement(2,"bar");
        db.insertMeasurement(c);

        Measurement j = db.selectMeasurement(2);
        assertEquals(c.get_measurementID()  ,j.get_measurementID());
    }
    public void testAddRecipeType(){
        RecipeType i = new RecipeType(1,"foo");
       assertEquals(1, db.insertRecipeType(i));
    }
    public void testDeleteRecipeType(){
        RecipeType i = new RecipeType(1,"foo");
        db.insertRecipeType(i);
        db.deleteRecipeType(i.get_typeID());
        assertEquals(null, db.selectRecipeType(i.get_typeID()));
    }

      public  void testAddRecipe()
      {
          Ingredient i=new Ingredient(1,"salt","salt");
          db.insertIngredient(i);
          Measurement m = new Measurement(1,"oz");
          db.insertMeasurement(m);
          Steps s=new Steps(1,"add",1,0);
           RecipeIngredients rI= new RecipeIngredients(1,1,1, 10);
          ArrayList<RecipeIngredients> r1 = new ArrayList<RecipeIngredients>();
          r1.add(rI);
          ArrayList<Steps> s1 = new ArrayList<Steps>();
          s1.add(s);
          Recipe r = new Recipe("test",r1,1,s1,"foo");
          // returns 0 on success 1 on failure
       assertEquals(0,   db.insertRecipe(r));

      }

    public void testSelRecipe()
    {
        Ingredient i=new Ingredient(1,"salt","salt");
        db.insertIngredient(i);
        Measurement m = new Measurement(1,"oz");
        db.insertMeasurement(m);
        Steps s=new Steps(1,"add",1,0);
        RecipeIngredients rI= new RecipeIngredients(-1,1,1, 10);
        ArrayList<RecipeIngredients> r1 = new ArrayList<RecipeIngredients>();
        r1.add(rI);
        ArrayList<Steps> s1 = new ArrayList<Steps>();
        s1.add(s);
        Recipe r = new Recipe("test",r1,1,s1,"foo");
        // returns 0 on success 1 on failure
        db.insertRecipe(r);
        Recipe test = db.selectRecipe(1);
        assertEquals(1,test.get_recipeID());
        assertEquals(1,(test.get_recipeIngredients().size()));
        assertEquals(1,(test.get_steps().size()));
        assertEquals("test",test.get_recipeName());




     }





        public void tearDown() throws Exception{
            db.close();
            super.tearDown();
        }
    }
