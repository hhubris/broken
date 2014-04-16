package com.starpoint.ws;


import com.starpoint.BusinessException;
import com.starpoint.domain.SimplePojo;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 */
public class DemoResourceImpl implements DemoResource {

    private static final List<SimplePojo> _all;

    static {
        String[] str = new String[] {
                "one", "fred", "joe", "paul", "tony"
        };

        List<SimplePojo> l = new ArrayList<SimplePojo>();

        for (int i = 0; i < 5; i++) {
            SimplePojo s = new SimplePojo();
            s.setId(i);
            s.setC(i % 2 == 0);
            s.setA(str[i]);
            l.add(s);
        }

        _all = Collections.unmodifiableList(l);
    }

    @Override
    public java.util.List<SimplePojo> getAll() {
        return _all;
    }

    @Override
    public SimplePojo getDomainObject(@PathParam("id") Integer id)
    {
        if (id < 0 || id > _all.size()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return _all.get(id);
    }

    @Override
    public List<SimplePojo> findSome(@PathParam("min") Integer min, @PathParam("max") Integer max) {
        if (min > max || min < 0 || max > _all.size()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return _all.subList(min, max);
    }
}
