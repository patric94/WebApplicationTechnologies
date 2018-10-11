import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './user/login/index';
import { RegisterComponent } from './user/register/index';
import { UserComponent } from './user/user.component';
import { HomeComponent} from './home/home.component';
import { AdminHomeComponent} from './admin/adminHome.component';
import { AuthGuard } from './_guards/index';
import { ProfileComponent } from "./my-profile/profile.component";
import { SettingsComponent } from "./settings/settings.component";
import { NetworkComponent } from "./network/network.component";
import { NotificationsComponent } from "./notifications/notifications.component";
import { JobsComponent } from "./jobs/jobs.component";
import { ProfilesComponent } from "./profiles/profiles.component";
import { MessagingComponent} from "./messaging/messaging.component";

const appRoutes: Routes = [

  { path: '', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'admin_home', component: AdminHomeComponent, canActivate: [AuthGuard] },
  { path: 'login', component: UserComponent,
    children: [{ path: '', component: LoginComponent }]},
  { path: 'register', component: UserComponent,
    children: [{ path: '', component: RegisterComponent }]},
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
  { path: 'settings', component: SettingsComponent, canActivate: [AuthGuard] },
  { path: 'messaging', component: MessagingComponent, canActivate: [AuthGuard] },
  { path: 'network', component: NetworkComponent, canActivate: [AuthGuard] },
  { path: 'notifications', component: NotificationsComponent, canActivate: [AuthGuard] },
  { path: 'jobs', component: JobsComponent, canActivate: [AuthGuard] },
  { path: 'profiles/:username', component: ProfilesComponent, canActivate: [AuthGuard]},
  // otherwise redirect to home
  //{ path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes, { useHash: true })
