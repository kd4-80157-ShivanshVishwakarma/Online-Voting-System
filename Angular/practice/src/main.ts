import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';
import { provideRouter } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';
import { appRoutes } from './app/app.routes';
import { CanDeactivateGuard } from './app/guards/can-deactivate.guard';


bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(appRoutes),
    provideHttpClient(),
    CanDeactivateGuard
  ],
}).catch((err) => console.error(err));
