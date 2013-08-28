package com.starpoint.pages;

import com.starpoint.domain.SimplePojo;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.RequestParameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.slf4j.Logger;

/**
 */
public class AjaxParam {

    @Inject
    private JavaScriptSupport javaScriptSupport;

    @Inject
    private ComponentResources componentResources;

    @Inject
    private Logger logger;

    @Inject
    private Request request;

    @Property
    private String textField;

    @Property
    private Boolean checkbox;

    void setupRender() {
        if (textField == null) {
            textField = "Hello";
            checkbox = true;
        }
    }

    @Import(library = "AjaxParam.js")
    void afterRender() {

        final String button1_url = "button1_url";
        JSONObject specs = new JSONObject(button1_url, componentResources.createEventLink("button1Press").toURI());
        javaScriptSupport.addInitializerCall("ajaxParam", specs);
    }

    /*
    JSONObject onButton1Press() {
        logger.info("a: " + request.getParameter("a") + " c: " + request.getParameter("c"));
        logger.info(request.getParameterNames().toString());
        return new JSONObject("response", "ok1");
    }

    JSONObject onButton1Press(@RequestParameter("a") String a, @RequestParameter("c") String c) {
        logger.info("a: " + a + " c: " + c);
        return new JSONObject("response", "ok2");
    }

    JSONObject onButton1Press(SimplePojo pojo) {
        logger.info(pojo.toString());
        return new JSONObject("response", "ok3");
    }

    JSONObject onButton1Press(JSONObject jsonObject) {
        SimplePojo pojo = SimplePojo.build(jsonObject);
        logger.info(pojo.toString());
        return new JSONObject("response", "ok4");
    }
    */


    JSONObject onButton1Press(@RequestParameter("params") String jsonString) {
        SimplePojo pojo = SimplePojo.build(new JSONObject(jsonString));
        logger.info(pojo.toString());
        return new JSONObject("response", "ok5");
    }

}
