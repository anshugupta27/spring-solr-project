import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AgGridModule } from 'ag-grid-angular';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FrontpageComponent } from './components/frontpage/frontpage.component';
import { HomeComponent } from './components/home/home.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { SinglePageComponent } from './components/single-page/single-page.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ReactiveFormsModule } from '@angular/forms';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { MessageCardComponent } from './components/message-card/message-card.component';
import { FilterComponent } from './components/filter/filter.component';



@NgModule({
  declarations: [
    AppComponent,
    FrontpageComponent,
    HomeComponent,
    NavBarComponent,
    SinglePageComponent,
    MessageCardComponent,
    FilterComponent
  ],
  imports: [
    
    BrowserModule,
    AppRoutingModule,
    AgGridModule.withComponents([]),
    HttpClientModule,
    NgbModule,
    ReactiveFormsModule,
    NgMultiSelectDropDownModule.forRoot()
    
  ],
 
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }






 



