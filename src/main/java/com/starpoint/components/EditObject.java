package com.starpoint.components;

import com.starpoint.domain.SimplePojo;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.TextField;
import org.slf4j.Logger;

import javax.inject.Inject;

/**
 */
public class EditObject {

    @Inject
    private Logger log;

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
        log.info("setVisible: " + visible);
        this.visible = visible;
    }

    /*
    void onValidate() {
        log.info("EditObject onValidate: " + domainObj);
    }

    void onValidateFromId() {
        log.info("EditObject onValidateFromId: " + domainObj.getId());
    }

    void onValidateFromA() {
        log.info("EditObject onValidateFromA: " + domainObj.getA());
    }
    */

    public SimplePojo getDomainObj() {
        return domainObj;
    }

    public void setDomainObj(SimplePojo domainObj) {
        this.domainObj = domainObj;
    }
}
