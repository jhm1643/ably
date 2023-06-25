package com.ably.util;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

public class PagingUtil {

    public static Slice<?> makeSlice(Pageable pageable, List<?> content) {
        var pageSize = pageable.getPageSize();

        boolean hasNext = false;
        if(content.size() > pageSize) {
            content.remove(pageSize);
            hasNext = true;
        }

        return new SliceImpl<>(content, pageable, hasNext);
    }
}
