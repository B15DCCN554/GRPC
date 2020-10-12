package validator;

import common.Property;
import entities.ObjectBean;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ObjectBeanValidate extends Validator {
    private final static String APP_ID = "app_id";

    public Map<String, String> validateInput(ObjectBean objectBean){
        Map<String, String> r = new HashMap<>(12);
        if(isEmpty(objectBean.getAppId())) {
            r.put("appId", "required");
        } else{
            String[] apps = Property.getInstance().getProperty(APP_ID).split(",");
            if(!Arrays.asList(apps).contains(objectBean.getAppId())){
                r.put("appId", "not found");
            }else{
                //FIXME fillter ipClient
            }
        }
        if(isEmpty(objectBean.getMnpId())) r.put("mnpId", "required");
        if(isEmpty(objectBean.getDestination())){
            r.put("destination", "required");
        }
        else{
            String regex = "^\\d{10}||\\d{11}$";
            if(!objectBean.getDestination().matches(regex)){
                r.put("destination", "invalid");
            }
        }
        if(isEmpty(objectBean.getProviderId())) r.put("providerId", "required");
        if(isEmpty(objectBean.getStatus())) r.put("status", "required");
        if(isEmpty(objectBean.getStartTime())) r.put("startTime", "required");
        if(isEmpty(objectBean.getCreateDate())) r.put("createDate", "required");
        if(isEmpty(objectBean.getModifyDate())) r.put("modifyDate", "required");
        if(isEmpty(objectBean.getCreateUser())) r.put("createUser", "required");
        if(isEmpty(objectBean.getOriginProviderId())) r.put("originProviderId", "required");
        if(isEmpty(objectBean.getProviderName())) r.put("providerName", "required");
        if(isEmpty(String.valueOf(objectBean.getRequestTime()))) r.put("requestTime", "required");
        return r;
    }

}
