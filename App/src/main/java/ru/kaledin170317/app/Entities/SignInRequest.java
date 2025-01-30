package ru.kaledin170317.app.Entities;

import lombok.Data;

@Data
public class SignInRequest {

    private String username;

    private String password;
}
