package com.article.api.controller;




import com.article.api.beans.Article;
import com.article.api.beans.AuthRequest;
import com.article.api.beans.Users;
import com.article.api.service.ArticleDetails;
import com.article.api.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private Users users;

    @Autowired
    private UserDetails userDetails;

    @Autowired
    private Article art;

    @Autowired
    private ArticleDetails articleDetails;

    @GetMapping("/")
    public String welcome() {
        return "worksss!!";
    }

    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }

    @PostMapping("/Register")
    public ResponseEntity<?> register(@RequestBody Users user) throws Exception{
        try {
            if (user.getEmail().isEmpty()||user.getPassword().isEmpty()||user.getUserName().isEmpty()||user.getAddress().isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("fill all");
            }
            Optional<Users> save = userDetails.saveUser(user);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("created");
        }catch(Exception ex){
            return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/articles")
    public ResponseEntity<?> addArticle(@RequestBody Article article) throws Exception{
        try{
            if (article.getAuthor().isEmpty() || article.getBody().isEmpty() || article.getTitile().isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("fill all");
            }
            Optional<Article> savedCandidate = articleDetails.saveArticle(article);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("created");

        } catch(Exception ex){
            return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/articles")
    public ResponseEntity<?> getArticles() throws Exception{
        try{
            List<Article> articles = articleDetails.getArticle();
            if (!articles.isEmpty()){
               return ResponseEntity.status(HttpStatus.OK).body(articles);
            }
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.NO_CONTENT);
        }catch(Exception ex){
            return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.CONFLICT);
        }
    }

}
