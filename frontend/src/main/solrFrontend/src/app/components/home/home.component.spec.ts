import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AppModule } from 'src/app/app.module';
import { HomeComponent } from './home.component';


describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports:[AppModule],
      // declarations: [ HomeComponent ]
    })
    .compileComponents()
    .then(()=>{
      fixture = TestBed.createComponent(HomeComponent);
      component = fixture.componentInstance;
    });
  });

  

  it('should create', () => {
    expect(component).toBeTruthy();
    console.log(component);
  });
});
