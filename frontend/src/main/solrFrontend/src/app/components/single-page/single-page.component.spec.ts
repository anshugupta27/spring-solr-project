import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AppModule } from 'src/app/app.module';
import { SinglePageComponent } from './single-page.component';

describe('SinglePageComponent', () => {
  let component: SinglePageComponent;
  let fixture: ComponentFixture<SinglePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports:[AppModule]
      // declarations: [ SinglePageComponent ]
    })
    .compileComponents()
    .then(()=>{

      fixture = TestBed.createComponent(SinglePageComponent);
      component = fixture.componentInstance;
    });
  });

 
  it('should create', () => {
    expect(component).toBeTruthy();
  });
});




