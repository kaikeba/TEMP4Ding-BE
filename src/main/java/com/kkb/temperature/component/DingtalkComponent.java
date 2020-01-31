package com.kkb.temperature.component;


import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGetJsapiTicketRequest;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiUserGetRequest;
import com.dingtalk.api.request.OapiUserGetuserinfoRequest;
import com.dingtalk.api.response.OapiGetJsapiTicketResponse;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.dingtalk.api.response.OapiUserGetuserinfoResponse;
import com.kkb.temperature.constant.TemperatureStatusEnum;
import com.kkb.temperature.domain.DingTalkUser;
import com.kkb.temperature.exception.KkbBusinessException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class DingtalkComponent {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${dingtalk.app.key}")
    private String appKey;

    @Value("${dingtalk.app.secret}")
    private String appSecret;

    public String getAccessToken() {
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(appKey);
        request.setAppsecret(appSecret);
        request.setHttpMethod(HttpMethod.GET.name());
        try {
            OapiGettokenResponse response = client.execute(request);
            if (response.isSuccess()) {
                return response.getAccessToken();
            } else {
                throw new KkbBusinessException(TemperatureStatusEnum.ACCESS_TOKEN_ERROR);
            }
        } catch (Exception e) {
            logger.error("request dingtalk error", e);
            throw new KkbBusinessException(TemperatureStatusEnum.ACCESS_TOKEN_ERROR);
        }
    }

    public String getUserId(String code) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/getuserinfo");
        OapiUserGetuserinfoRequest request = new OapiUserGetuserinfoRequest();
        request.setCode(code);
        request.setHttpMethod(HttpMethod.GET.name());
        OapiUserGetuserinfoResponse response = null;
        try {
            response = client.execute(request, getAccessToken());
            if (response.isSuccess()) {
                return response.getUserid();
            } else {
                throw new KkbBusinessException(TemperatureStatusEnum.USERID_ERROR);
            }
        } catch (Exception e) {
            logger.error("request dingtalk userId error", e);
            throw new KkbBusinessException(TemperatureStatusEnum.USERID_ERROR);
        }
    }

    public DingTalkUser getUserInfoByCode(String code) {
        return getUserInfo(getUserId(code));
    }

    public DingTalkUser getUserInfo(String userId) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/get");
        OapiUserGetRequest request = new OapiUserGetRequest();
        request.setUserid(userId);
        request.setHttpMethod(HttpMethod.GET.name());
        try {
            OapiUserGetResponse response = client.execute(request, getAccessToken());
            if (response.isSuccess()) {
                DingTalkUser dingTalkUser = new DingTalkUser();
                dingTalkUser.setUserId(userId);
                dingTalkUser.setName(response.getName());
                dingTalkUser.setTitle(response.getPosition());
                dingTalkUser.setMobile(response.getMobile());
                dingTalkUser.setAvatar(response.getAvatar());
                dingTalkUser.setDepartment(StringUtils.join(response.getDepartment(), ","));
                return dingTalkUser;
            } else {
                throw new KkbBusinessException(TemperatureStatusEnum.USER_INFO_ERROR);
            }
        } catch (Exception e) {
            logger.error("request dingtalk user info error", e);
            throw new KkbBusinessException(TemperatureStatusEnum.USER_INFO_ERROR);
        }
    }

    public String getJsApiTicket() {
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/get_jsapi_ticket");
        OapiGetJsapiTicketRequest req = new OapiGetJsapiTicketRequest();
        req.setTopHttpMethod("GET");
        try {
            OapiGetJsapiTicketResponse response = client.execute(req, getAccessToken());
            if (response.isSuccess()) {
                return response.getTicket();
            } else {
                throw new KkbBusinessException(TemperatureStatusEnum.JSAPI_TICKET_ERROR);
            }
        } catch (Exception e) {
            logger.error("request dingtalk jsapi ticket error", e);
            throw new KkbBusinessException(TemperatureStatusEnum.JSAPI_TICKET_ERROR);
        }
    }
}
