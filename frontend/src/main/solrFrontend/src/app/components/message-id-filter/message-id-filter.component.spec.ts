import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AppModule } from 'src/app/app.module';
import { MessageIdFilterComponent } from './message-id-filter.component';

describe('MessageIdFilterComponent', () => {
  let component: MessageIdFilterComponent;
  let fixture: ComponentFixture<MessageIdFilterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports:[AppModule]
      // declarations: [ MessageIdFilterComponent ]
    })
    .compileComponents()
    .then(()=>{

      fixture = TestBed.createComponent(MessageIdFilterComponent);
      component = fixture.componentInstance;
    });
  });

 

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
