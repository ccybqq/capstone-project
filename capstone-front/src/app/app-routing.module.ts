import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BloodSearchComponent } from './component/blood-search/blood-search.component';
import { LoginComponent } from './component/login/login.component';
import { RegisterComponent } from './component/register/register.component';
import { UserProfileComponent } from './component/user-profile/user-profile.component';

const routes: Routes = [
  { path: 'profile', component: UserProfileComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'search', component: BloodSearchComponent },
  { path: 'request', component: BloodSearchComponent },
  { path: 'registry/add', component: BloodSearchComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
