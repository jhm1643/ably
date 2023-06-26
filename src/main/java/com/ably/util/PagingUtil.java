package com.ably.util;

import com.ably.dto.common.pagination.type.PaginationType;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

public class PagingUtil {

    public static Slice<?> makeSlice(PaginationType type, Pageable pageable, List<?> content, JPAQuery<Long> countQuery) {
        if(PaginationType.SCROLL == type) {
            var pageSize = pageable.getPageSize();
            var hasNext = false;
            if(content.size() > pageSize) {
                content.remove(pageSize);
                hasNext = true;
            }
            return new SliceImpl<>(content, pageable, hasNext);
        } else {
            return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetchOne());
        }
    }
}
