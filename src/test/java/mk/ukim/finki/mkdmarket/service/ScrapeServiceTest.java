package mk.ukim.finki.mkdmarket.service;

import mk.ukim.finki.mkdmarket.domain.enums.Category;
import mk.ukim.finki.mkdmarket.domain.enums.City;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScrapeServiceTest {

    private final ScrapeService service = new ScrapeService();

    @Test
    public void getItemsFromPazar3() {
        service.getItemsFromReklama5("samsung",1,0);
    }
}