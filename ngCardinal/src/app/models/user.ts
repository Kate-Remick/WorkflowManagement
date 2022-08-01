export class User {

  id: number;
  firstName: string;
  lastName: string;
  email: string;
  username: string;
  password: string;
  imgUrl: string;
  googleId: string;
  createdAt: Date | null;
  enabled: boolean;
  role: string;

  constructor(
   id: number = 0,
   firstName: string = '',
   lastName: string = '',
   email: string = '',
   username: string = '',
   password: string = '',
   imgUrl: string = '',
   googleId: string = '',
   createdAt: Date | null = null,
   enabled: boolean = false,
   role: string = ''
  ){
  this.id = id;
  this.firstName = firstName;
  this.lastName = lastName;
  this.email = email;
  this.username = username;
  this.password = password;
  this.imgUrl = imgUrl;
  this.googleId = googleId;
  this.createdAt = createdAt;
  this.enabled = enabled;
  this.role = role;
  }
}
