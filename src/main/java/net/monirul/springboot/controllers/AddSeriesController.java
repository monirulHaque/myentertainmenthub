package net.monirul.springboot.controllers;

import net.monirul.springboot.controllers.dto.SeriesDto;
import net.monirul.springboot.models.User;
import net.monirul.springboot.services.SeriesService;
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
@RequestMapping("/myserieslist/addseries")
public class AddSeriesController {

    private final SeriesService seriesService;

    private final UserService userService;

    public AddSeriesController(SeriesService seriesService, UserService userService) {
        this.seriesService = seriesService;
        this.userService = userService;
    }

    @GetMapping
    public String addSeries(Model model) {
        model.addAttribute("series", new SeriesDto());
        model.addAttribute("seriesList", seriesService.getAllSeries());
        return "addseries";
    }

    @RequestMapping("/add/{id}")
    public String addSeriesToList(@PathVariable String id, Model model) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userService.getUserByEmail(username);
        userService.addSeriesList(user, seriesService.findSeriesByApiId(id));
        return "redirect:/myserieslist?success";
    }

    @PostMapping
    public String getSeriesInfo(@ModelAttribute("series") SeriesDto seriesDto, Model model) throws IOException, JSONException, ParseException {
        String seriesName = seriesDto.getName();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/search/tv?api_key=52884d23cdfe49146b058fc02d1cf8ff&language=en-US&query=" + seriesName + "&page=1&include_adult=true")
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

        ArrayList<SeriesDto> listData = new ArrayList<SeriesDto>();

        for (int i=0;i<jArray.length();i++){
            JSONObject Data =(JSONObject)(jArray.getJSONObject(i));
            String releaseDate = "";
            try {
                releaseDate = Data.get("first_air_date").toString();
            } catch (Exception e) {
                releaseDate = "";
            }
            SeriesDto seriesDto2 = new SeriesDto(Data.get("id").toString(),
                    Data.get("name").toString(),
                    new StringBuilder().append("https://image.tmdb.org/t/p/original").append(Data.get("backdrop_path").toString()).toString(),
                    Data.get("original_language").toString(),
                    Data.get("overview").toString().substring(0, Math.min(Data.get("overview").toString().length(), 100)),
                    Data.get("vote_average").toString(),
                    releaseDate
                    );

            if (!seriesService.existsSeriesByApiId(seriesDto2.getApiId())) {
                seriesService.save(seriesDto2);
            }
            listData.add(seriesDto2);
        }

        model.addAttribute("seriesList", listData);

        return "addseries";
    }


}
