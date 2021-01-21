import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArtigoViewComponent } from './artigo-view.component';

describe('ArtigoViewComponent', () => {
  let component: ArtigoViewComponent;
  let fixture: ComponentFixture<ArtigoViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ArtigoViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ArtigoViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
