package com.starpoint.pages

import org.apache.tapestry5.annotations.Persist
import org.apache.tapestry5.annotations.Property
import org.apache.tapestry5.ioc.annotations.Inject
import org.apache.tapestry5.json.JSONObject
import org.slf4j.Logger

import java.text.SimpleDateFormat

/**
 */
class DatePicker {

    @Persist
    @Property
    private Date date;

    @Inject
    private Logger logger;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    def void setupRender() {
        if (date == null) date = new Date()
    }

    /* this formats the date when a user selects a date */
    def JSONObject getParams() {
        return new JSONObject("dateFormat", "mm/dd/yy")
    }

    def String getFormattedDate() {
        return date == null ? "" : dateFormat.format(date)
    }
}
