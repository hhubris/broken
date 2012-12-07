package com.starpoint.pages;

import com.starpoint.datasource.SortDemoDataSource;
import org.apache.tapestry5.annotations.Property;

/**
 */
public class SortDemo {

    @Property
    private Object unused;

    public SortDemoDataSource getSource() {
        return new SortDemoDataSource(25);
    }
}
