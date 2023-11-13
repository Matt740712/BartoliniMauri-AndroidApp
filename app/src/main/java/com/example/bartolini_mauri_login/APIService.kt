import com.example.bartolini_mauri_login.models.LoginResponse
import com.example.bartolini_mauri_login.models.customer.CustomerResponse
import com.example.bartolini_mauri_login.models.policy.PolicyResponse
import retrofit2.Call
import retrofit2.http.*

interface APIService {
    @FormUrlEncoded
    @POST("Login")

    //Todo:login() è un metodo di tipo POST che richiede username e password come parametri.
    // Restituisce un oggetto di tipo Call<LoginResponse>, e la chiamata asincrona

    fun login(
        @Field("username") username : String,
        @Field("password") password : String
    ) : Call<LoginResponse>

    //Todo:richiede un header (headers) e un parametro (idCustomer). Restituisce un oggetto di tipo Call<CustomerResponse>
    // e chiede le informazioni del cliente

    @GET("Customer")
    fun getCustomer(
        @HeaderMap headers : Map<String, String>,
        @Query("idCustomer") id: String
    ) : Call<CustomerResponse>

    //Todo: richiede un header (headers) e un parametro (idCustomer). Restituisce un oggetto di tipo Call<PolicyResponse>,
    // per ottenere la risposta con le informazioni sulle policy del cliente.

    @GET("Contract")
    fun getContracts(
        @HeaderMap headers: Map<String, String>,
        @Query("idCustomer") id: String
    ) : Call<PolicyResponse>
}