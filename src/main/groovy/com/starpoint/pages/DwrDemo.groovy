package com.starpoint.pages

import org.apache.tapestry5.annotations.Import
import org.apache.tapestry5.ioc.annotations.Inject
import org.apache.tapestry5.json.JSONObject
import org.apache.tapestry5.services.Request
import org.apache.tapestry5.services.javascript.JavaScriptSupport
import org.springframework.context.ApplicationContext

/**
 */
class DwrDemo {

    @Inject
    JavaScriptSupport javascriptSupport;

    @Inject
    Request request;

    private ApplicationContext ac;

// @Import(library = ["context:/dwr/interface/Broken1.js", "context:/dwr/engine.js", "context:/dwr/util.js"])
    @Import(library = "DwrDemo.js")
    void afterRender() {

        javascriptSupport.importJavaScriptLibrary(String.format("%s/dwr/engine.js", request.getContextPath()));
        javascriptSupport.importJavaScriptLibrary(String.format("%s/dwr/util.js", request.getContextPath()));
        javascriptSupport.importJavaScriptLibrary(String.format("%s/dwr/interface/Broken1.js", request.getContextPath()));

        JSONObject specs = new JSONObject();
        javascriptSupport.addInitializerCall("DwrDemo", specs);
    }
}
