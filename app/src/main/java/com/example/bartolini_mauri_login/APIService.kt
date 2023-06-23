import com.example.bartolini_mauri_login.models.LoginResponse
import com.example.bartolini_mauri_login.models.customer.CustomerResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface APIService {
    @FormUrlEncoded
    @POST("Login")
    fun login(
        @Field("username") username : String,
        @Field("password") password : String
    ) : Call<LoginResponse>

    @GET("Customer/{id}")
    fun getCustomer(
        @HeaderMap headers : Map<String, String>,
        @Path("id") id: String
    ) : Call<CustomerResponse>
}