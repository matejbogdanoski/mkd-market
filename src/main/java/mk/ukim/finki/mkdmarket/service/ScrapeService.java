package mk.ukim.finki.mkdmarket.service;

import mk.ukim.finki.mkdmarket.domain.Ad;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScrapeService {

    private Map<Integer,String> cities = new HashMap<>();

    public ScrapeService() {
        cities.put(0, "Цела-Македонија");
        cities.put(1,"skopje");
        cities.put(2,"bitola");
        cities.put(3,"kumanovo");
        cities.put(4,"prilep");
        cities.put(5,"tetovo");
        cities.put(6,"veles");
        cities.put(7,"stip");
        cities.put(8,"ohrid");
        cities.put(9,"gostivar");
        cities.put(10,"strumica");
        cities.put(11,"kavadarci");
        cities.put(12,"kocani");
        cities.put(13,"kicevo");
        cities.put(14,"struga");
        cities.put(15,"radovis");
        cities.put(16,"gevgelija");
        cities.put(17,"debar");
        cities.put(18,"kriva-palanka");
        cities.put(19,"sveti-nikole");
        cities.put(20,"negotino");
        cities.put(21,"delcevo");
        cities.put(22,"vinica");
        cities.put(23,"resen");
        cities.put(24,"probistip");
        cities.put(25,"berovo");
        cities.put(26,"kratovo");
        cities.put(27,"krusevo");
        cities.put(28,"makedonski-brod");
        cities.put(29,"valandovo");
        cities.put(30,"demir-hisar");
    }

    public Set<?> getAllItems(String searchQuery, int pageNum, int city){
        String pazar3url;
        if(pageNum == 1) {
            pazar3url = String.format("https://www.pazar3.mk/Огласи/%s?Search=%s", cities.get(city), searchQuery);
        }
        else{
            pazar3url = String.format("https://www.pazar3.mk/Огласи/%s?Search=%s?Page=%d", cities.get(city), searchQuery,pageNum);
        }
        String reklama5url;
        if(city == 0){
            reklama5url = String.format("https://www.reklama5.mk/Search?q=%s&page=%d",searchQuery,pageNum );
        }else{
            reklama5url = String.format("https://www.reklama5.mk/Search?q=%s&page=%d&city=%d",searchQuery,pageNum ,city);
        }
        try{
            Set<Ad> ads = Jsoup.connect(pazar3url).get().getElementsByClass("row row-listing ").parallelStream()
                    .filter(el -> el.getElementsByClass("label label-default isstore").isEmpty())
                    .map(element ->{
                        Ad ad = new Ad();
                        ad.setImageUrl(element.select("div.span2-ad-img-list.first-span.img-col.multi > span > a > img").attr("src"));
                        ad.setOriginalUrl("https://www.pazar3.mk" + element.select("div.title.span-col-title > h2 > a").attr("href"));
                        ad.setPrice(element.getElementsByClass("list-price").text());
                        ad.setCityCategory(element.getElementsByClass("link-html5 nobold").eachText());
                        ad.setName(element.select("div.title.span-col-title > h2 > a").text());
                        ad.setOriginSite("Pazar 3");
                        return ad;
                    }).collect(Collectors.toSet());

            ads.addAll(Jsoup.connect(reklama5url).get().getElementsByClass("OglasResults").parallelStream()
                    .filter(el -> !el.className().contains("topAdResults"))
                    .map(element ->{
                        Ad ad = new Ad();
                        ad.setImageUrl("https://" + element.select("#imgContainer > a > img").attr("src").substring(2));
                        if(ad.getImageUrl().equals("https://ontent/images/noImage2.jpg")) ad.setImageUrl("https://acacia-wood.com/themes/jtherczeg-multi//assets/images/acacia/empty-img.png");
                        ad.setOriginalUrl("https://www.reklama5.mk" + element.select("#imgContainer > a").attr("href"));
                        ad.setName(element.select("div.searchAdBox > div.text-left.text-info > a").text());
                        ad.setPrice(element.select("div.searchAdBox > div.clear-padding.left > div.text-left.text-success").text());
                        ad.setCityCategory(List.of(
                                element.select("div.searchAdBox > div.text-center.clear-padding.right > div.text-right.text-to-bottom > p.clear-margin").text().replaceAll(" > .*$",""),
                                element.select("div.searchAdBox > div.text-center.clear-padding.right > div.text-right.text-to-bottom > p.adCategoryName > a").text()
                        ));
                        ad.setOriginSite("Reklama 5");
                        return ad;
                    }).collect(Collectors.toSet()));

            return ads;
        }catch (Exception e){
            return Set.of("{error : something went wrong}");
        }

    }

}
