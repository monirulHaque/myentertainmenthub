package net.monirul.springboot.controllers;

import net.monirul.springboot.controllers.dto.MovieDto;
import net.monirul.springboot.models.User;
import net.monirul.springboot.services.MovieService;
import net.monirul.springboot.services.UserService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

@Controller
@RequestMapping("/mymovielist/addmovie")
public class AddMovieController {

    private final MovieService movieService;

    @Autowired
    private UserService userService;

    public AddMovieController(MovieService movieService) {
        this.movieService = movieService;
    }

//    @Value("${api.key}")
//    private String apiKey;

    @GetMapping
    public String addMovie(Model model) {
        model.addAttribute("movie", new MovieDto());
        model.addAttribute("movies", movieService.getAllMovies());
        return "addmovie";
    }

    @RequestMapping("/add/{id}")
    public String addMovieToList(@PathVariable String id, Model model) throws Exception {
        System.out.println("don't know what's wrong");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userService.getUserByEmail(username);
        userService.addMovieList(user, movieService.findMovieByApiId(id));
        return "redirect:/mymovielist?success";
    }

//    @RequestMapping("/{movieName}")
//    public String getMovieInfo(@PathVariable String movieName, Model model) throws IOException, JSONException, ParseException {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
////        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        headers.set("x-rapidapi-host", "imdb-internet-movie-database-unofficial.p.rapidapi.com");
//        headers.set("x-rapidapi-key", "52dbad0660msh7c9868a8b978f13p1da039jsnff4c54727758");
//        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
////        HttpEntity<String> entity = new HttpEntity<String>(headers);
//        Object moviedetails = restTemplate.exchange(
//                "https://imdb-internet-movie-database-unofficial.p.rapidapi.com/search/inception", HttpMethod.GET, entity, String.class
//        );

//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url("https://imdb-internet-movie-database-unofficial.p.rapidapi.com/search/" + movieName)
//                .get()
//                .addHeader("x-rapidapi-host", "imdb-internet-movie-database-unofficial.p.rapidapi.com")
//                .addHeader("x-rapidapi-key", apiKey)
//                .addHeader("Content-Type", "application/json")
//                .build();

    @PostMapping
    public String getMovieInfo(@ModelAttribute("movie") MovieDto movieDto, Model model) throws IOException, JSONException, ParseException {
        String movieName = movieDto.getName();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/search/movie?api_key=52884d23cdfe49146b058fc02d1cf8ff&language=en-US&query=" + movieName + "&page=1&include_adult=false")
                .get()
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();

        System.out.println(response);
        String jsonData = response.body().string();
        JSONObject Jobject = null;
        try {
            Jobject = new JSONObject(jsonData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray jArray = Jobject.getJSONArray("results");

        ArrayList<MovieDto> listData = new ArrayList<MovieDto>();
//        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MMM-dd");

        for (int i=0;i<jArray.length();i++){
            JSONObject Data =(JSONObject)(jArray.getJSONObject(i));
            MovieDto movieDto2 = new MovieDto(Data.get("id").toString(),
                    Data.get("title").toString(),
                    new StringBuilder().append("https://image.tmdb.org/t/p/original").append(Data.get("backdrop_path").toString()).toString(),
                    Data.get("original_language").toString(),
                    Data.get("overview").toString().substring(0, Math.min(Data.get("overview").toString().length(), 100)),
                    Data.get("vote_average").toString(),
//                    new SimpleDateFormat("yyyy-MM-dd").parse(Data.get("release_date").toString()
                    Data.get("release_date").toString());

            if (!movieService.existsMovieByApiId(movieDto2.getApiId())) {
                movieService.save(movieDto2);
            }
            listData.add(movieDto2);
        }
//        JSONObject Data =(JSONObject)(jArray.getJSONObject(0));
//        model.addAttribute("moviename", Data.get("title"));
        model.addAttribute("movies", listData);

        return "addmovie";
    }

}