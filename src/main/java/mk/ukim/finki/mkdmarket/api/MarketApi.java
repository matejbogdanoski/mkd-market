package mk.ukim.finki.mkdmarket.api;

import mk.ukim.finki.mkdmarket.service.ScrapeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/scrape")
public class MarketApi {

    private final ScrapeService service;

    public MarketApi(ScrapeService service) {
        this.service = service;
    }

    @GetMapping("/items/{pageNum}/{searchQuery}/{city}")
    public Set<?> getItems(@PathVariable int pageNum, @PathVariable String searchQuery,
                           @PathVariable int city){
        return service.getAllItems(searchQuery,pageNum,city);
    }

}
