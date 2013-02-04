package com.starpoint.mixins;

import org.apache.tapestry5.Field;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectContainer;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

/**
 */
@Import(library = "trim.js")
public class Trim {

    @InjectContainer
    private Field component;

    @Inject
    private JavaScriptSupport javaScriptSupport;

    void afterRender() {
        JSONObject specs = new JSONObject("id", component.getClientId());
        javaScriptSupport.addInitializerCall("trimValue", specs);
    }

}
