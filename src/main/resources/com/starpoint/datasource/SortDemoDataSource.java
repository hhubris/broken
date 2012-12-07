package com.starpoint.datasource;

import com.starpoint.domain.User;
import org.apache.tapestry5.grid.ColumnSort;
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.grid.SortConstraint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 */
public class SortDemoDataSource implements GridDataSource {

    private List<User> users;

    public SortDemoDataSource(int maxUsers) {
        users = new ArrayList<User>();

        for (int i = 0; i < maxUsers; i++) {
            users.add(new User("user" + String.format("%04d", i)));
        }
    }

    @Override
    public int getAvailableRows() {
        return users.size();
    }

    @Override
    public void prepare(int startIndex, int endIndex, List<SortConstraint> sortConstraints) {

        Collections.sort(users, new SortDemoSorter(sortConstraints.get(0).getColumnSort()));
    }

    @Override
    public Object getRowValue(int index) {
        return users.get(index);
    }

    @Override
    public Class getRowType() {
        return User.class;
    }

    static class SortDemoSorter implements Comparator<User> {

        private ColumnSort columnSort;
        private Logger logger = LoggerFactory.getLogger(SortDemoSorter.class);

        SortDemoSorter(ColumnSort columnSort) {
            this.columnSort = columnSort;
            logger.info("Created SortDemoSort with columnSort " + columnSort.name());
        }

        @Override
        public int compare(User user1, User user2) {

            return columnSort == ColumnSort.ASCENDING ?
                    user1.getUsername().compareTo(user2.getUsername()) :
                    user2.getUsername().compareTo(user1.getUsername());
        }
    }
}
