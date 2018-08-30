/*
 * (c)BOC
 */
package net.pis.dto;


import java.io.Serializable;
import java.util.Map;
/**
 *
 * @author jh,Seo
 */
public class StatefulDTO implements Serializable {

    private String stateId;

    private String code;
    private String message;
    private Boolean done;

    private String routingKey;

    // private JCoFunction function;

    private Map<String, Object> map;

    public StatefulDTO() {

        this.done = false;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

/*    public JCoFunction getFunction() {
        return function;
    }

    public void setFunction(JCoFunction function) {
        this.function = function;
    }*/

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public Boolean isDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
