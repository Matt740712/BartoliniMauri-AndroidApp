import com.example.bartolini_mauri_login.models.LoginResponse
import com.example.bartolini_mauri_login.models.customer.CustomerResponse
import com.example.bartolini_mauri_login.models.policy.PolicyResponse
import retrofit2.Call
import retrofit2.http.*

interface APIService {
    @FormUrlEncoded
    @POST("Login")
    fun login(
        @Field("username") username : String,
        @Field("password") password : String
    ) : Call<LoginResponse>

    @GET("Customer")
    fun getCustomer(
        @HeaderMap headers : Map<String, String>,
        @Query("idCustomer") id: String
    ) : Call<CustomerResponse>

    @GET("Contract")
    fun getContracts(
        @HeaderMap headers: Map<String, String>,
        @Query("idCustomer") id: String
    ) : Call<PolicyResponse>
}