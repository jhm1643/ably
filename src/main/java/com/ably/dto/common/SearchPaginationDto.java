package com.ably.dto.common;

import org.springframework.data.domain.Pageable;

public interface SearchPaginationDto {

    Pageable getPageable();
}
