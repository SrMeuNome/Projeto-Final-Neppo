import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddArtigoComponent } from './add-artigo.component';

describe('AddArtigoComponent', () => {
  let component: AddArtigoComponent;
  let fixture: ComponentFixture<AddArtigoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddArtigoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddArtigoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
