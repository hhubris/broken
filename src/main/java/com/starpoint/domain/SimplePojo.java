package com.starpoint.domain;

import org.apache.tapestry5.json.JSONObject;

/**
 */
public class SimplePojo {
    private String a;
    private Boolean c;

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
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("SimplePojo");
        sb.append("{a='").append(a).append('\'');
        sb.append(", c='").append(c).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimplePojo that = (SimplePojo) o;

        if (a != null ? !a.equals(that.a) : that.a != null) return false;
        if (c != null ? !c.equals(that.c) : that.c != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = a != null ? a.hashCode() : 0;
        result = 31 * result + (c != null ? c.hashCode() : 0);
        return result;
    }

    public static SimplePojo build(JSONObject jsonObject) {
        SimplePojo result = new SimplePojo();
        result.setA(jsonObject.getString("a"));
        result.setC(jsonObject.getBoolean("c"));
        return result;
    }

}
