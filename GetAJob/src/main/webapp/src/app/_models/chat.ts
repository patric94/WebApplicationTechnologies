import { User } from "./user";

export class Chat {
  id: number;
  user_one: User;
  user_two: User;
  view_username: string;
  view_fullname: string;
  view_photo_url: string;
}
