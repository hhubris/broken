package com.starpoint.dwr.dto

import org.directwebremoting.annotations.DataTransferObject
import org.directwebremoting.annotations.RemoteProperty

/**
 */
@DataTransferObject
class TestDs {

    @RemoteProperty
    Integer id;

    @RemoteProperty
    String name;

    @RemoteProperty
    ArrayList<Integer> numbers;
}
