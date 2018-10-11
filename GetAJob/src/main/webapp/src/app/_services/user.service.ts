import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Education, User, UserPOJO, Work, Skill, UserAdminPOJO} from '../_models/index';

@Injectable()
export class UserService {
    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<UserAdminPOJO[]>('/api/users');
    }

    getOtherUsers(username: string){
      return this.http.get<UserPOJO[]>('/api/get/other/users/'+ username);

    }

    loadUser(username: string){
      return this.http.get<UserPOJO>('/api/users/pojo/'+ username);

    }

    getByUsername(username: string) {
        return this.http.get<User>('/api/users/' + username);
    }

    exportXML(list: UserAdminPOJO[]){
      return this.http.post('/api/selected/users/xml', list);
    }

    exportAllXML(){
      return this.http.get('/api/users/xml');
    }

    ban(user:UserAdminPOJO){
      return this.http.put('/api/ban/user/'+ user.username, '');
    }

    create(user: User) {
        var register  = {
            'username': user.username,
            'password': user.password,
            'firstname': user.firstname,
            'lastname': user.lastname,
            'photo_url': "../../assets/anonymous.png",
            'email': user.email,
            'curr_pos': user.curr_pos,
            'curr_inst': user.curr_inst
        };
        return this.http.post('/api/register', register);
    }

    updateEmail(username: string, newEmail: string){
      var update  = {
        'username': username,
        'email': newEmail
      };
      return this.http.put('/api/users/change/email', update);
    }

    updatePassword(username: string, currentPassword: string, newPassword: string){
      var update  = {
        'username': username,
        'oldPassword': currentPassword,
        'newPassword': newPassword
      };
      return this.http.put('/api/users/change/password', update);
    }

    //education
    getEducation(username: string){
      return this.http.get<Education[]>('/api/education/' + username);
    }

    getPublicEducation(username: string){
      return this.http.get<Education[]>('/api/public/education/' + username);
    }

    addEducation(username: string, education: Education){
      var create  = {
        'name': education.name,
        'startYear': education.startYear,
        'endYear': education.endYear,
        'public': true
      };
      return this.http.post('/api/education/add/' + username, create);
    }

    setEducationPublic(id: number){
      return this.http.put('/api/education/set/public/'+ id, '');
    }

    setEducationPrivate(id: number){
      return this.http.put('/api/education/set/private/'+ id, '');
    }

    //work
    getWork(username: string){
      return this.http.get<Work[]>('/api/work/' + username);
    }

    getPublicWork(username: string){
      return this.http.get<Work[]>('/api/public/work/' + username);
    }

    addWork(username: string, work: Work){
      var create  = {
        'name': work.name,
        'startYear': work.startYear,
        'endYear': work.endYear,
        'public': true
      };
      return this.http.post('/api/work/add/' + username, create);
    }

    setWorkPublic(id: number){
      return this.http.put('/api/work/set/public/'+ id, '');
    }

    setWorkPrivate(id: number){
      return this.http.put('/api/work/set/private/'+ id, '');
    }

    //skill
    getSkills(username: string){
      return this.http.get<Skill[]>('/api/skill/' + username);
    }

    getPublicSkills(username: string){
      return this.http.get<Skill[]>('/api/public/skill/' + username);
    }

    addSkill(username: string, skill: Skill){
      var create  = {
        'name': skill.name,
        'public': true
      };
      return this.http.post('/api/skill/add/' + username, create);
    }

    setSkillPublic(id: number){
      return this.http.put('/api/skill/set/public/'+ id, '');
    }

    setSkillPrivate(id: number){
      return this.http.put('/api/skill/set/private/'+ id, '');
    }
}
