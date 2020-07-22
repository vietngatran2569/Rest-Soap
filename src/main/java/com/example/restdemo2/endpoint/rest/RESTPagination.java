package com.example.restdemo2.endpoint.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class RESTPagination {
    private int page;
    private int limit;
    private int totalPages;
    private long totalItems;

    /*public RESTPagination(int page, int limit, long totalItems) {
        this.page = page;
        this.limit = limit;
        if (limit != 0) {
            this.totalPages = (totalItems % limit == 0) ? (int) (totalItems / limit) : ((int) (totalItems / limit) + 1);
        } else this.totalPages = 0;
        this.totalItems = totalItems;
    }*/

    public RESTPagination(int page, int limit, int totalPages, long totalItems) {
        this.page = page;
        this.limit = limit;
        if (limit != 0) {
            this.totalPages = (totalItems % limit == 0) ? (int) (totalItems / limit) : ((int) (totalItems / limit) + 1);
        } else this.totalPages = totalPages;
        this.totalItems = totalItems;
    }
}