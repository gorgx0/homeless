package com.gorg.homeless;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path = "types")
public interface TypesRestRepository extends CrudRepository<TypeOfPlace, Long> {
}
