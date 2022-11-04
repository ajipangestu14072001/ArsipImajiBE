package com.example.arsipimajibe.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String refreshToken;
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
    private String nama;
    private String telepon;

    public JwtResponse(String accessToken, String refreshToken, Long id, String username, String email, List<String> roles, String nama, String telepon) {
        this.token = accessToken;
        this.refreshToken = refreshToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.nama = nama;
        this.telepon = telepon;
    }

}
