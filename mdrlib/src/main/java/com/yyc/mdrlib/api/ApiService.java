package com.yyc.mdrlib.api;


import com.yyc.mdrlib.entity.BaseEntity;
import com.yyc.mdrlib.entity.Token;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by 小鹿 on 2017/2/4.
 */

public interface ApiService {


        @GET("visual/login")
    Observable<BaseEntity<Token>> Login();
//
//    @POST("login/Regedit")
//    @Multipart
//    Observable<RemoteResult<JsonObject>> regedit(@PartMap HashMap<String, RequestBody> params);

    /*@GET("themes")
    Observable<TitileBean> getLeftContent();

    @GET("theme/{id}")
    Observable<ThemesBean> onLoad(@Path("id") int id);

    @GET("news/latest")
    Observable<HomeBean> onLatest();

    @GET("news/{id}")
    Observable<WebBean> onLoadWeb(@Path("id") int id);

    @GET("news/before/{date}")
    Observable<HomeBean> onGone(@Path("date") String date);
//  at.zhihu.com/api/4/section/34/before/1465772400
    @GET("theme/{id}/before/{timestamp}")
    Observable<ThemesBean> onThemeGone(@Path("timestamp") String timestamp, @Path("id") int id);

    @GET("story-extra/{id}")
    Observable<WebExtraBean> onLoadExtra(@Path("id") int id);*/


}
