package com.poly.sof3021.ph29788.common.core;

import org.springframework.data.domain.Page;

public interface CrudService<T, U> {

    Page<U> getAll(int page, int size, String sortField, String sortOrder);

    U getById(Long id);

    U save(T requestDTO);

    U update(T requestDTO, Long id);

    void delete(Long id);

}
