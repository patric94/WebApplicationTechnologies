import { Comment } from './index';

export class Post {
  id: number;
  username: string;
  content: string;
  photo_url: string;
  status: number;
  comments: Comment[];
  likeAmount: number;
}
