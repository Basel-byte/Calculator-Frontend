import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Event} from "@angular/router";

@Component({
  selector: 'app-calculator',
  templateUrl: './calculator.component.html',
  styleUrls: ['./calculator.component.css']
})
export class CalculatorComponent implements OnInit {

  @Input() expression = "";

  @Output() calculateEvent = new EventEmitter();

  constructor() {

  }

  displayOnScreen(buttonClicked : string) {
    this.expression += buttonClicked;
  }

  erase() {
    this.expression = "";
  }

  removeLastElement() {
    this.expression = this.expression.slice(0, -1);
  }

  oneDividedByX() {
    this.expression = "1÷" + this.expression;
  }

  square() {
    this.expression += "^2";
  }

  oppositeSign() {
    this.expression += "×-1";
  }

  evaluate() {
    this.expression = encodeURIComponent(this.expression);
    this.calculateEvent.emit(this.expression);
  }




  ngOnInit(): void {
  }

}
