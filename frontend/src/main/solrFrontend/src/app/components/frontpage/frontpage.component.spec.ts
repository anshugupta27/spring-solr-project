import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AppModule } from 'src/app/app.module';
import { FrontpageComponent } from './frontpage.component';

describe('FrontpageComponent', () => {
  let component: FrontpageComponent;
  let fixture: ComponentFixture<FrontpageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports:[AppModule]
      // declarations: [ FrontpageComponent ]
    })
    .compileComponents()
    .then(()=>{
      fixture = TestBed.createComponent(FrontpageComponent);
      component = fixture.componentInstance;
    });
  });



  it('should create', () => {
    expect(component).toBeTruthy();
  });
});


