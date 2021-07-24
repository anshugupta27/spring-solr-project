import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FrontpageComponent } from './components/frontpage/frontpage.component';
import { SinglePageComponent } from './components/single-page/single-page.component';
import { HomeComponent} from './components/home/home.component';
const routes: Routes = [
  {
    path : '',
    component : HomeComponent 
     },
     {
       path:"singleMail",
       component: SinglePageComponent
     }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }





