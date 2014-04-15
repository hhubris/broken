package com.starpoint.components;

import com.starpoint.domain.SimplePojo;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.slf4j.Logger;

import javax.inject.Inject;

/**
 */
public class EditObject {

    @Inject
    private Logger log;

    @Property
    @Parameter(required = true)
    SimplePojo domainObj;

    @Property
    @Parameter(required = false, value = "0")
    Integer idx;

    Boolean visible;

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    /*
    void onValidate() {
        log.info("EditObject onValidate: " + domainObj);
    }
    */
}
