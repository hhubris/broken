package com.starpoint.datasource;

import com.starpoint.domain.User;
import org.apache.tapestry5.grid.ColumnSort;
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.grid.SortConstraint;
import org.mvel2.MVEL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 */
public class SortDemoDataSource implements GridDataSource {

    private List<User> users;

    public SortDemoDataSource(int maxUsers) {
        Random random = new Random(21020824L);  // not so random on purpose

        users = new ArrayList<User>();

        for (int i = 0; i < maxUsers; i++) {
            String id = String.format("%04d", i);
            String randomId = String.format("%04d", random.nextInt(maxUsers));
            users.add(new User("user" + id, "first" + id, "last" + randomId));
        }
    }

    @Override
    public int getAvailableRows() {
        return users.size();
    }

    @Override
    public void prepare(int startIndex, int endIndex, List<SortConstraint> sortConstraints) {

        SortConstraint sortConstraint = sortConstraints.get(0);
        Collections.sort(users, new SortDemoSorter(sortConstraint.getPropertyModel().getPropertyName(),
                sortConstraint.getColumnSort()));
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

        private String property;
        private ColumnSort columnSort;
        private Logger logger = LoggerFactory.getLogger(SortDemoSorter.class);

        SortDemoSorter(String property, ColumnSort columnSort) {
            this.property = property;
            this.columnSort = columnSort;
            logger.info("Created SortDemoSort with property: " + property + ", columnSort: " + columnSort.name());
        }

        @Override
        public int compare(User user1, User user2) {

            String v1 = (String)MVEL.getProperty(property, user1);
            String v2 = (String)MVEL.getProperty(property, user2);

            return columnSort == ColumnSort.ASCENDING ? v1.compareTo(v2) : v2.compareTo(v1);
        }
    }
}
