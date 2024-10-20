package network; // Adjust this to your actual package structure

import models.SignupRequest;
import models.SignupResponse;
import models.User;
import models.MyResponse;
import models.SearchResponse;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    // Method for user signup
    @POST("signup.php") // Keeping the PHP file extension
    Call<SignupResponse> signup(@Body SignupRequest signupRequest);

    // Method for retrieving some data from an endpoint
    @GET("your_endpoint.php")
    Call<MyResponse> getData();

    // Method for fetching a user by their ID
    @GET("users.php/{id}") // Assuming you're using a PHP-based API route
    Call<User> getUserById(@Path("id") int userId);

    // Method for searching items using a query parameter
    @GET("search.php")
    Call<SearchResponse> searchItems(@Query("query") String searchTerm);

    // Method for updating the user profile
    @POST("update_profile.php") // Keeping the PHP file extension
    Call<JSONObject> updateProfile(@Body JSONObject profileData);

    // Method for deleting a user account
    @POST("delete_account.php")
    Call<JSONObject> deleteAccount(@Body JSONObject deleteRequest);
}
