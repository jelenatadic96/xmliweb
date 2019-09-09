import { Component } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent {

  // public loggedIn: boolean = false;
  // isAdmin = false;
  // loggedUser: User = new User();

  // constructor(private authService: AuthService, private userService: UserService, private router: Router){
  //   let res = localStorage.getItem('token');
  //   if(res != null){
  //     this.loggedIn = true;
  //     this.loggedUser.email = this.authService.getUsername(res);
  //     this.userService.findByEmail(this.loggedUser.email).subscribe(
  //       s=>{
  //         this.loggedUser = s;
  //         this.loggedUser.role.forEach(element =>
  //           {
  //           if(element.roleName == "ROLE_ADMIN"){
  //             this.isAdmin = true;
  //               }
  //             }
  //           )
  //       }
  //     )

  //   }
  // }

  // logOut(){
  //   this.authService.logout();
  //   this.loggedIn = false;
  //   this.router.navigate(['login']);
  // }

  // goToHome(){
  //   if(this.isAdmin){
  //     this.router.navigate(['users']);
  //   }
  //   else{
  //     this.router.navigate(['address']);
  //   }
  // }
  
}
