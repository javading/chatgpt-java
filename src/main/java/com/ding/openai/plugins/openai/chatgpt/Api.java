package com.ding.openai.plugins.openai.chatgpt;

import com.ding.openai.entity.billing.CreditGrantsResponse;
import com.ding.openai.entity.chat.ChatCompletion;
import com.ding.openai.entity.chat.ChatCompletionResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 *
 */
public interface Api {

    String DEFAULT_API_HOST = "https://api.openai.com/";


    /**
     * chat
     */
    @POST("v1/chat/completions")
    Single<ChatCompletionResponse> chatCompletion(@Body ChatCompletion chatCompletion);


    /**
     * 余额查询
     */
    @GET("dashboard/billing/credit_grants")
    Single<CreditGrantsResponse> creditGrants();


}
