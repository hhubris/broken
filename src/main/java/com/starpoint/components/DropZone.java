package com.starpoint.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.apache.tapestry5.upload.services.UploadedFile;
import org.slf4j.Logger;

/**
 */
public class DropZone {

    @Inject
    private Logger logger;

    private UploadedFile file;

    @Parameter(required= true, defaultPrefix = BindingConstants.LITERAL)
    private String domId;

    @Inject
    private JavaScriptSupport javaScriptSupport;

    @Inject
    private ComponentResources resources;

    @Inject
    private Request request;

    @Import(stylesheet = "context:/style/dropzone.css", library = { "context:/js/dropzone.js" })
    void afterRender() {

        JSONObject specs = new JSONObject(
                "element", domId,
                "url", resources.createEventLink("dropZoneUpload").toURI());

        javaScriptSupport.addInitializerCall("dropzone", specs);
    }

    @Log
    void onSuccess() {
        logger.info(file.getFilePath() + " : " + file.getSize());

    }

    void onDropZoneUpload() {
        for (String name : request.getParameterNames()) {
            logger.info(name + " : " + request.getParameter(name));
        }

    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
}
