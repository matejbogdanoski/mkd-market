import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Ad} from "./ad";

@Injectable({
  providedIn: 'root'
})
export class ScrapeService {

  constructor(private http: HttpClient) { }

  getItems(pageNum:number,searchQuery:string,city:number):Observable<Ad[]>{
    return this.http.get<Ad[]>(`/api/scrape/items/${pageNum}/${searchQuery}/${city}`);
  }

}
