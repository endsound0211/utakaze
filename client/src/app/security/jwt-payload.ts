import {User} from './user';

export class JwtPayload {
  sub: string;
  aud: string;
  iss: string;
  iat: number;
  exp: number;
  refresh: number;
  data: User;
}
