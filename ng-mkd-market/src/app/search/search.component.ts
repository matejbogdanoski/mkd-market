import { Component, OnInit } from '@angular/core';
import {Ad} from "../ad";
import {ScrapeService} from "../scrape.service";
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  ads: Ad[] = [];
  searchInput = new FormControl('');
  selectedValue: string;
  currPage: number;
  loading: boolean;
  cities: string[] = [
    "Skopje","Bitola","Kumanovo","Prilep","Tetovo","Veles","Stip","Ohrid","Gostivar","Strumica","Kavadarci",
    "Kocani","Kicevo","Struga","Radovis","Gevgelija","Debar","Kriva Palanka","Sveti Nikole","Negotino",
    "Delcevo","Vinica","Resen","Probistip","Berovo","Kratovo","Krusevo","Makedonski Brod","Valandovo",
    "Demir Hisar"
  ];


  constructor(private _service: ScrapeService) { }

  ngOnInit() {
    this.currPage = 0;
    this.loading = false;
  }

  onClick(){
    this.loading = true;
    this._service.getItems(1,this.searchInput.value,this.cities.indexOf(this.selectedValue) + 1)
      .subscribe(it => {
        this.ads = it;
        this.currPage = 1;
        this.loading = false;
      });

  }

  nextPage(){
    this.loading = true;
    window.scroll(0,0);
    this._service.getItems(this.currPage+1,this.searchInput.value,this.cities.indexOf(this.selectedValue) + 1)
      .subscribe(it => {
        this.ads = it;
        this.currPage++;
        this.loading = false;
      });
  }

  prevPage(){
    if(this.currPage == 1) return;
    this.loading = true;
    window.scroll(0,0);
    this._service.getItems(this.currPage-1,this.searchInput.value,this.cities.indexOf(this.selectedValue) + 1)
      .subscribe(it => {
        this.ads = it;
        this.currPage--;
        this.loading = false;
      });
  }

  redirect(url:string){
    window.open(url,"_blank");
  }

}
