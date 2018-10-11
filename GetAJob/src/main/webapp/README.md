# Angular Project Structure

### Alerts

  - **Path: /app/_directives/alert.component.html**

    The alert component template contains the html for displaying alert messages at the top of the page.

  - **Path: /app/_directives/alert.component.ts**

    The alert component passes alert messages to the template whenever a message is received from the alert service. It does this by subscribing to the alert service's getMessage() method which returns an Observable.


  - **Path: /app/_services/alert.service.ts**

     The alert service enables any component in the application to display alert messages at the top of the page via the alert component.

     It has methods for displaying success and error messages, and a getMessage() method that returns an Observable that is used by the alert component to subscribe to notifications for whenever a message should be displayed.

### Auth Guard

  - **Path: /app/_guards/auth.guard.ts**

    The auth guard is used to prevent unauthenticated users from accessing restricted routes, in this case it's used in **app.routing.ts** to protect the home page route.

### JWT Interceptor

   - **Path: /app/_helpers/jwt.interceptor.ts**

     The JWT Interceptor intercepts http requests from the application to add a JWT auth token to the Authorization header if the user is logged in.

     It's implemented using the HttpInterceptor class. By extending the HttpInterceptor class you can create a custom interceptor to modify http requests before they get sent to the server.

     Http interceptors are added to the request pipeline in the providers section of the app.module.ts file.

### User Model

   - **Path: /app/_models/user.ts**

          export class User {
            id: number;
            username: string;
            password: string;
            fullName: string;
            email: string;
            token: string;
          }

### Authentication Service

   - **Path: /app/_services/authentication.service.ts**

      The authentication service is used to login and logout of the application, to login it posts the users credentials to the api and checks the response for a JWT token, if there is one it means authentication was successful so the user details including the token are added to local storage.

      The logged in user details are stored in local storage so the user will stay logged in if they refresh the browser and also between browser sessions until they logout. If you don't want the user to stay logged in between refreshes or sessions the behaviour could easily be changed by storing user details somewhere less persistent such as session storage or in a property of the authentication service.

### User Service

  - **Path: /app/_services/user.service.ts**

    The user service contains a standard set of CRUD methods for managing users, it acts as the interface between the Angular application and the backend api.


### User Component Template
  - **Path: /app/user/user.component.html**

    The user component template contains a menu bar and the login/register tab template.

### Login Component Template

  - **Path: /app/user/login/login.component.html**

    The login component template contains a login form with username and password fields. It displays validation messages for invalid fields when the submit button is clicked. On submit the login() method is called as long as the form is valid.

### Login Component

  - **Path: /app/user/login/login.component.ts**

    The login component uses the authentication service to login and logout of the application. It automatically logs the user out when it initializes (ngOnInit) so the login page can also be used to logout.

### Register Component Template

   - **Path: /app/user/register/register.component.html**

      The register component template contains a simple registration form with fields for  username, password, email and fullName. It displays validation messages for invalid fields while typing. On submit the register() method is called if the form is valid.

### Register Component

   - **Path: /app/user/register/register.component.ts**

     The register component has a single register() method that creates a new user with the user service when the register form is submitted.

### App Component Template

   - **Path: /app/app.component.html**

     The app component template is the root component template of the application, it contains a router-outlet directive for displaying the contents of each view based on the current route, and an alert directive for displaying alert messages from anywhere in the system.

### App Component

   - **Path: /app/app.component.ts**

     The app component is the root component of the application, it defines the root tag of the app as <app-root></app-root> with the selector property.

### App Module

   - **Path: /app/app.module.ts**

     The app module defines the root module of the application along with metadata about the module.

     This is where the fake backend provider is added to the application, to switch to a real backend simply remove the providers located under the comment "// providers used to create fake backend".
