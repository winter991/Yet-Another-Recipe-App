package test;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.android.yet_another_recipe_app.Ingredient;

import database.RecipeDatabaseHelper;

public class RecipeDatabaseHelperTest  extends AndroidTestCase {
        private RecipeDatabaseHelper db;

        public void setUp(){
            RenamingDelegatingContext context
                    = new RenamingDelegatingContext(getContext(), "test_");
            db = new RecipeDatabaseHelper(context);
        }

        public void testAddIngredent(){
            Ingredient i = new Ingredient(-1,"foo","bar");
            assertEquals(1, db.insertIngredient(i));
        }
        public void testUpdateIngredent()
        {
            Ingredient i = new Ingredient(1,"foo","bar");
            db.insertIngredient(i);
            i.set_description("pizza");
            db.updateIngredient(i);
            Ingredient j = db.selectIngredient(1);
            assertEquals("pizza",j.get_description());
        }

        public void tearDown() throws Exception{
            db.close();
            super.tearDown();
        }
    }
