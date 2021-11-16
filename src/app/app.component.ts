import {Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'calculator';
  result : any;
  finalResult = "";
  private url : string;

  constructor(private http : HttpClient) {
      this.url = "http://localhost:8080/calculate";
  }

  evaluateExpression(expression : string) {
    this.http.get(this.url, {
      responseType: 'text',
      params: {
        arithmeticExpression: expression
      },
      observe: 'response'
    }).subscribe(response => {
      this.result = response.body
      console.log(this.result)
      this.finalResult = this.result;
    })


  }






}
