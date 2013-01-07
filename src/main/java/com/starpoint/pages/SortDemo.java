package com.starpoint.pages;

import com.starpoint.datamodel.TestDataModel;
import com.starpoint.datasource.SortDemoDataSource;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.internal.services.AjaxPartialResponseRenderer;
import org.apache.tapestry5.internal.services.PageRenderQueue;
import org.apache.tapestry5.internal.services.ajax.AjaxFormUpdateController;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.TypeCoercer;
import org.apache.tapestry5.services.Environment;
import org.apache.tapestry5.services.TranslatorSource;
import org.got5.tapestry5.jquery.internal.DataTableModel;

/**
 */
public class SortDemo {

    @Property
    private Object unused;

    @Inject
    private TypeCoercer typeCoercer;

    @Inject
    private TranslatorSource translatorSource;

    @Inject
    private Environment environment;

    @Inject
    private PageRenderQueue pageRenderQueue;

    @Inject
    private AjaxFormUpdateController ajaxFormUpdateController;

    @Inject
    private AjaxPartialResponseRenderer ajaxPartialResponseRenderer;

    public SortDemoDataSource getSource() {
        return new SortDemoDataSource(25);
    }

    public DataTableModel getMyModel() {
        return new TestDataModel(typeCoercer, translatorSource, environment, pageRenderQueue,
                ajaxFormUpdateController, ajaxPartialResponseRenderer);
    }
}
