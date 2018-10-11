import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

import { AppComponent }  from './app.component';
import { routing } from './app.routing';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';

import { AlertComponent } from './_directives/index';
import { AuthGuard } from './_guards/index';
import { JwtInterceptor } from './_helpers/index';
import { AlertService, AuthenticationService, UserService, TimelineService, MessagingService, UploadFileService} from './_services/index';
import { HomeComponent } from './home/index';
import { LoginComponent } from './user/login/index';
import { RegisterComponent } from './user/register/index';
import { UserComponent } from './user/user.component';
import { AdminHomeComponent} from './admin/adminHome.component';
import { MenuComponent } from './menu/menu.component'
import { ProfileComponent } from './my-profile/profile.component'
import { SettingsComponent } from './settings/settings.component'
import { NetworkComponent } from './network/network.component'
import { NetworkService } from './_services/network.service';
import { NotificationsComponent } from './notifications/notifications.component';
import { NotificationService } from './_services/index';
import { JobsComponent } from "./jobs/jobs.component";
import { JobsDialogComponent } from "./jobs/jobs.component";
import { JobService } from "./_services/index";
import { ProfilesComponent } from "./profiles/profiles.component";
import { PhotoDialogComponent} from "./my-profile/profile.component";
import { EducationDialogComponent } from "./my-profile/profile.component";
import { WorkDialogComponent } from "./my-profile/profile.component";
import { SkillDialogComponent } from "./my-profile/profile.component";
import { ApplicantsDialogComponent } from "./jobs/jobs.component";
import { LikersDialogComponent, PostsDeleteDialogComponent } from "./home/home.component"
import { MessagingComponent } from "./messaging/messaging.component";
import { FormUploadComponent } from "./my-profile/form-upload.component";
import { DetailsUploadComponent } from "./my-profile/details-upload.component";


@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    routing,
    BrowserAnimationsModule,
    MaterialModule,
    ReactiveFormsModule
  ],
  declarations: [
    AppComponent,
    AlertComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    UserComponent,
    AdminHomeComponent,
    MenuComponent,
    ProfileComponent,
    SettingsComponent,
    NetworkComponent,
    NotificationsComponent,
    JobsComponent,
    JobsDialogComponent,
    ApplicantsDialogComponent,
    ProfilesComponent,
    PhotoDialogComponent,
    EducationDialogComponent,
    WorkDialogComponent,
    SkillDialogComponent,
    LikersDialogComponent,
    PostsDeleteDialogComponent,
    MessagingComponent,
    FormUploadComponent,
    DetailsUploadComponent
  ],
  providers: [
    AuthGuard,
    AlertService,
    AuthenticationService,
    NetworkService,
    NotificationService,
    JobService,
    TimelineService,
    MessagingService,
    UploadFileService,
    UserService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    },

  ],
  entryComponents: [PostsDeleteDialogComponent, LikersDialogComponent, ApplicantsDialogComponent, SkillDialogComponent, WorkDialogComponent, JobsDialogComponent, PhotoDialogComponent, EducationDialogComponent],
  bootstrap: [AppComponent]
})

export class AppModule { }
