package net.monirul.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mymovielist/addmovie")
public class AddMovieController {

    @GetMapping
    public String addMoviePage() {
        return "mymovielist";
    }
}
