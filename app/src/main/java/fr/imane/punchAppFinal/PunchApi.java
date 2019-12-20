package fr.imane.punchAppFinal;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PunchApi {

    String BASE_URL = "https://raw.githubusercontent.com/IM94/Punchlines/master/";

    @GET("punch")
    Call<List<Punchline>> getPosts();


}
