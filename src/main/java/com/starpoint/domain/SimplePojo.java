package com.starpoint.domain;

import org.apache.tapestry5.json.JSONObject;

/**
 */
public class SimplePojo {

    private static String NULL_VALUE = "<**__null__**>";

    private Integer id;
    private String a;
    private Boolean c;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public Boolean getC() {
        return c;
    }

    public void setC(Boolean c) {
        this.c = c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimplePojo)) return false;

        SimplePojo that = (SimplePojo) o;

        if (a != null ? !a.equals(that.a) : that.a != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (a != null ? a.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new JSONObject()
                .put("id", nullSafe(id))
                .put("a", nullSafe(a))
                .put("c", nullSafe(c))
                .toString();
    }

    private String nullSafe(Object obj) {
        return obj == null ? NULL_VALUE : obj.toString();
    }

    public static SimplePojo build(JSONObject jsonObject) {
        SimplePojo result = new SimplePojo();
        result.setId((Integer)translateNull(jsonObject.get("id")));
        result.setA((String)translateNull(jsonObject.get("a")));
        result.setC((Boolean)translateNull(jsonObject.get("c")));
        return result;
    }

    private static Object translateNull(Object value) {
        return value == null || value.toString().equals(NULL_VALUE) ? null : value;
    }

}
