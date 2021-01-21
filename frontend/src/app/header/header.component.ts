import { Component, OnInit, Input } from '@angular/core';
import { Router, ɵROUTER_PROVIDERS } from '@angular/router';

@Component({
  selector: 'header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  @Input() nameUser: string = ''
  @Input() typeUser: string = ''

  constructor(private router: Router) { }

  goTo(path: string) : void
  {
    this.router.navigateByUrl(path);
  }

  ngOnInit(): void {
  }

}
