package com.article.api.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Article {
    private int ArticleId;
    private String titile;
    private String Body;
    private String author;

    // we can add timestamp

}
