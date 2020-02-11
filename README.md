# FoodFirst-Android

You have to migrate this project to your own firebase account.Please refer video to do the same.
video : https://www.youtube.com/watch?v=nHEkIedZgi0


After migration you have to enable authentication and sign in methods.
STEPS : 
  1. Go to your project in firebase.
  2. select authentication tab.
  3. select Sign-In methods.
  4. Now, enalbe email-password and phone in sign in providers.
  
Enable realtime Database :
 STEPS :
   1. Go to your project in firebase.
   2. select Database tab.
   3. select Realtime databse -> create database.
   4. select rules and change read and write values to true as below.
   
             {
            "rules": {
            ".read": true,
            ".write": true
          }
        }
 
 Enable storage:
  STEPS:
   1. Go to your project in firebase.
   2. select strage tab.
   3. select get started.
   4. finish createing.
   5. select Ruls tab and make changes as below.
      
              rules_version = '2';
              service firebase.storage {
              match /b/{bucket}/o {
              match /{allPaths=**} {
              allow read, write;
            }
          }
        }
        
    NOW add FoodFirst-restaurant app in the same firebase project.

