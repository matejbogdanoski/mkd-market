package mk.ukim.finki.mkdmarket.domain;


import java.util.List;

public class Ad {
    private String imageUrl;
    private List<String> cityCategory;
    private String originalUrl;
    private String price;
    private String name;
    private String originSite;

    public String getOriginSite() {
        return originSite;
    }

    public void setOriginSite(String originSite) {
        this.originSite = originSite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getCityCategory() {
        return cityCategory;
    }

    public void setCityCategory(List<String> cityCategory) {
        this.cityCategory = cityCategory;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
