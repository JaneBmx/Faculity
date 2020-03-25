package com.vlasova.specification.faculity;

import com.vlasova.entity.faculity.Faculity;

import java.util.Set;

public interface FaculitySpecification {
    Set<Faculity> query();
}
