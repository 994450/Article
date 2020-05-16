package com.article.api.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_TBL")
public class Users {
    @Id
    private int id;
    private String userName;
    private String password;
    private String email;
    private String Address;

    // we can add timestamp
}
