package com.gz.controller;

import com.jfinal.aop.Duang;
import com.jfinal.core.Controller;
import com.jfinal.kit.Kv;
import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.cache.IAccessTokenCache;
import com.jfinal.wxaapp.api.WxaUserApi;
import com.jfinal.wxaapp.jfinal.WxaController;

/**
 * Created by gongzhen on 2018/6/19.
 */
@RestController
public class MiniProgramController {
    // 微信用户接口api
    protected WxaUserApi wxaUserApi = Duang.duang(WxaUserApi.class);
    /**
     * 登陆接口
     */
    public void login() {
        public String login(@RequestParam String code) {
            if (StrKit.isBlank(code)) {
                Kv data = Kv.by("errcode", 500)
                        .set("errmsg", "code is blank");
                return data.toString();
            }
            // 获取SessionKey
            ApiResult apiResult = wxaUserApi.getSessionKey(code);
            // 返回{"session_key":"nzoqhc3OnwHzeTxJs+inbQ==","expires_in":2592000,"openid":"oVBkZ0aYgDMDIywRdgPW8-joxXc4"}
            if (!apiResult.isSucceed()) {
                return apiResult.getJson();
            }
        }

    }

    /**
     * 服务端解密用户信息接口
     * 获取unionId
     */
    public void info() {
        public String info(@RequestParam String signature, @RequestParam String rawData, @RequestParam String encryptedData, @RequestParam String iv) {

        // 参数空校验 不做演示
        // 利用 appId 与 accessToken 建立关联，支持多账户
        IAccessTokenCache accessTokenCache = ApiConfigKit.getAccessTokenCache();
        String sessionId = getHeader("wxa-sessionid");
        if (StrKit.isBlank(sessionId)) {
            Kv data = Kv.by("errcode", 500)
                    .set("errmsg", "wxa_session Header is blank");
            return data.toString();
        }
        String sessionJson = accessTokenCache.get("wxa:session:" + sessionId);
        if (StrKit.isBlank(sessionJson)) {
            Kv data = Kv.by("errcode", 500)
                    .set("errmsg", "wxa_session sessionJson is blank");
            return data.toString();
        }
        ApiResult sessionResult = ApiResult.create(sessionJson);
        // 获取sessionKey
        String sessionKey = sessionResult.get("session_key");
        if (StrKit.isBlank(sessionKey)) {
            Kv data = Kv.by("errcode", 500)
                    .set("errmsg", "sessionKey is blank");
            return data.toString();
        }
        // 用户信息校验
        boolean check = wxaUserApi.checkUserInfo(sessionKey, rawData, signature);
        if (!check) {
            Kv data = Kv.by("errcode", 500)
                    .set("errmsg", "UserInfo check fail");
            return data.toString();
        }
        // 服务端解密用户信息
        ApiResult apiResult = wxaUserApi.getUserInfo(sessionKey, encryptedData, iv);
        if (!apiResult.isSucceed()) {
            return apiResult.getJson();
        }
        // 如果开发者拥有多个移动应用、网站应用、和公众帐号（包括小程序），可通过unionid来区分用户的唯一性
        // 同一用户，对同一个微信开放平台下的不同应用，unionid是相同的。
        String unionId = apiResult.get("unionId");
        return "{}";
    }

}
