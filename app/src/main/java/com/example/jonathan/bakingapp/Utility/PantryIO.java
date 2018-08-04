package com.example.jonathan.bakingapp.Utility;

        import android.content.Context;
        import android.util.Log;

        import com.android.volley.RequestQueue;
        import com.android.volley.toolbox.JsonArrayRequest;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.Volley;
        import com.android.volley.Response;
        import com.android.volley.Request;
        import com.example.jonathan.bakingapp.Data.SingleRecipe;
        import com.example.jonathan.bakingapp.Data.Step;
        import com.example.jonathan.bakingapp.Data.Ingredient;

        import org.json.JSONException;
        import org.json.JSONObject;
        import org.json.JSONArray;

        import java.io.IOException;
        import java.util.ArrayList;


public class PantryIO {

    private Context context;
    private String urlRequest;
    private String networkResult;
    private ArrayList<SingleRecipe> MasterList;
    // Volley
    RequestQueue requestQueue;

    public PantryIO(Context context, String urlRequest){
        this.context = context;
        this.urlRequest =urlRequest;
        MasterList = new ArrayList<SingleRecipe>();

        // Setup Volley
        requestQueue = Volley.newRequestQueue(context);

        JsonArrayRequest arrayreq = new JsonArrayRequest(urlRequest,
                // The second parameter Listener overrides the method onResponse() and passes
                //JSONArray as a parameter
                new Response.Listener<JSONArray>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Retrieves array of recipe object
                            //JSONArray recipeList = response.getJSONArray(0);
                            // Iterate through array getting recipes
                            for(int i = 0; i < response.length(); i++) {
                                // Get Array Object
                                JSONObject recipe = response.getJSONObject(i);

                                // Retrieve Base data && make Recipe
                                String ID = recipe.getString("id");
                                String name = recipe.getString("name");
                                String servings = recipe.getString("servings");
                                String imgUrl = recipe.getString("image");
                                SingleRecipe newRecipe = new SingleRecipe(Integer.parseInt(ID),name,Integer.parseInt(servings),imgUrl);

                                // Parse Ingredients & add to recipe
                                JSONArray ingredients = recipe.getJSONArray("ingredients");
                                for(int x = 0; x < ingredients.length(); x++) {
                                    // Retrieve Ingredients
                                    JSONObject nextIngredient = ingredients.getJSONObject(x);

                                    String quantity = nextIngredient.getString("quantity");
                                    String measure = nextIngredient.getString("measure");
                                    String ingredient = nextIngredient.getString("ingredient");
                                    Ingredient newIngredient = new  Ingredient(quantity, measure, ingredient);
                                    newRecipe.addIngredient(newIngredient);
                                }

                                // Parse Steps & add to recipe
                                JSONArray steps = recipe.getJSONArray("steps");
                                for(int x = 0; x < steps.length(); x++) {
                                    // Retrieve steps
                                    JSONObject nextStep = steps.getJSONObject(x);

                                    String id = nextStep.getString("id");
                                    String shortD = nextStep.getString("shortDescription");
                                    String description = nextStep.getString("description");
                                    String videoURL = nextStep.getString("videoURL");
                                    String thumb = nextStep.getString("thumbnailURL");
                                    Step newStep = new Step(Integer.parseInt(id), shortD, description, videoURL, thumb);
                                    newRecipe.addStep(newStep);
                                }

                                // Add recipe to master list
                                MasterList.add(newRecipe);
                            }
                        }
                        // Try and catch are included to handle any errors due to JSON
                        catch (JSONException e) {
                            // If an error occurs, this prints the error to the log
                            e.printStackTrace();
                        }
                    }
                },
                // The final parameter overrides the method onErrorResponse() and passes VolleyError
                //as a parameter
                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                }
        );
        // Adds the JSON array request "arrayreq" to the request queue
        requestQueue.add(arrayreq);


    }

}
