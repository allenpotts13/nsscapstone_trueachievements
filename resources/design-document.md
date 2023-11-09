# TrueAchievements Group Service Design

## 1. Problem Statement
Xbox is a well-known brand and one that is synonymous with gaming. 
A great website, TrueAchievements.com, leans into the achievement's gamers earn while gaming. 
They add their own score based on many factors, including difficulty, rarity, and other secret sauce algorithms.
However, the one thing its lacking in is the ability to group your games up to help find and organize your information.

I wish to build a TrueAchievements Service that will allow users to import their data, be able to search and even create groups of games to help better narrow their scopes. 
The groups feature is where the biggest benefit will be seen. 
You will be able to create a group for a variety of ideas and concepts. 
Some potential ideas are Current Games in Progress, Favorite Games, Groups based on a certain genre. 
This allows you to modify and tweak your groupings as needed and let you have better control over your data. 
Since the data itself will be read only, this way the state of the information won't change, but how you use and organize the data will be the key feature. 
There will be a central hub to display all the stats, so you can get a snapshot overview. 
It will be able to truly be a complementary tool for those who are always hunting down the next achievement! 

## 2. Use Cases

U1. As a customer, I want to create a new, empty group with a given name.

U2. As a customer, I want to be able to pull up any group with a given group ID.

U3. As a customer, I want to be able to add a game to the group from within the group page. 

U4. As a customer, I want to be able to delete a game from the group. 

U5. As a customer, I want to be able to see my entire game library. 

U6. As a customer, I want to be able to search my entire game library. 

U7. As a customer, I want to be able to see my statistics. (Total games, Hours played, Total Achievement Score, etc.)

U8. As a customer, I want to be able to add a game from within the game details page. 

U9. As a customer, I want to be able to add a tag to a game to define a genre. 

STRETCH

U10. As a customer, I want to be able to import new data as needed. 

## 3. Project Scope
The scope of this project is to create, add, and delete groups. It will help aggregate and manage your data while giving
you more freedom over how things are structured. The data within will only pertain to the user logged in and not be open 
to add other users. 

## 4. Proposed Architecture Overview
We will use API Gateway and Lambda to create six endpoints (`GetUserStatsLambda`, `CreateGroupLambda`, 
`GetGroupLambda`, `GetGameLambda`, `GetAllGroupsLambda`, `GetAllGamesLambda`, `AddGameToGroupLambda`, and `GetGamesInGroupLambda`)
that will handle the creation, update, and retrieval of groups to satisfy our
requirements.

We will store games available for groups in a table in DynamoDB. Groups
themselves will also be stored in DynamoDB. For simpler game list retrieval, we
will store the list of games in a given group directly in the groups
table.

We will also provide a web interface for users to manage
their groups and games. A main page providing a list view of all of their relevant
statistics will be their central hub. A main page will allow users to create new groups. 

# 5. API

## 5.1. Public Models

```
// GroupModel

String groupName;
List<Games> gameList;
```

```
// GameModel

String uniqueId;
String gameName;	
String platform;	
String gameURL;	
Integer achievementsWonNoDlc;	
Integer maxAchievementsNoDlc;	
Integer achievementsWonIncludeDlc;
Integer maxAchievementsIncludeDlc;	
Integer gamerScoreWonNoDlc;
Integer maxGamerScoreNoDlc;	
Integer gamerScoreWonIncludeDlc;	
Integer maxGamerscoreIncludeDlc;	
Integer trueAchievementWonNoDlc;	
Integer maxTrueAchievementNoDlc;
Integer trueAchievementWonIncludeDlc;
Integer maxTrueAchievementIncludeDlc;	
Integer myCompletionPercentage;	
String completionDate;	
Integer challengesWon;	
Integer maxChallenges;	
Integer hoursPlayed;	
Float myRating;	
Float siteRating;	
Float myRatio;	
Float siteRatio;	
String ownershipStatus;	
String playStatus;	
String format;	
String completionEstimate;	
String walkthrough;	
List<String> gameNotes;	
String contestStatus;
```

```
// UserStatsModel

Integer gamerScore;
Integer trueAchievementScore;
Integer hoursPlayed;
Integer numberOfGamesOwned;
Integer myCompletionPercentage;
```

## 5.2. Get Group Endpoint
* Accepts `GET` requests to `/groups/:groupName`
* Accepts a group name and returns the corresponding GroupModel.
    * If the given Group is not found, will throw a
      `GroupNotFoundException`

## 5.3 Create Group Endpoint
* Accepts `POST` requests to `/groups`
* Accepts data to create a new group with a group name, with an empty list of games. Returns the new group.
* For security concerns, we will validate the provided group name does not
  contain any invalid characters: `" ' \`
    * If the group name contains any of the invalid characters, will throw an
      `InvalidAttributeException`

## 5.4 Get Game Endpoint
* Accepts `GET` requests to `/games/:uniqueId/`
* Accepts a uniqueId and returns the corresponding GameModel.
    * If the given unique ID is not found, will throw a
      `GameNotFoundException`

## 5.5 Add Game to Group Endpoint
* Accepts `POST` requests to `/groups/:groupName/games`
* Accepts a group name and a game to be added. The game is specified by its uniqueId and gameName.
    * If the group is not found, will throw a `GroupNotFoundException`
    * If the uniqueId or gameName doesn't exist, will throw a
      `GameNotFoundException`

## 5.6 Get Games in Group Endpoint
* Accepts `GET` requests to `/groups/:groupName/games`
* Retrieves all games of a group with the given name
    * Returns the game list in alphabetical order by gameName
* If the group name is found, but contains no games, the games list will be
  empty
* If the group name is not found, will throw a `GroupNotFoundException`

## 5.7 Get All Groups Endpoint
* Accepts `GET` requests to `/groups`
* Retrieves all groups

## 5.8 Get All Games Endpoint
* Accepts `GET` requests to `/games`
* Retrieves all games

## 5.9 Get User Stats Endpoint
* Accepts `GET` requests to `/statistics`
* Retrieves user stats from various data points

# 6. Tables

### 6.1. 'groups'
```
groupName // partition key, string
gameList // list
```

### 6.2. 'games'
```
uniqueId // partition key, string
gameName // string	
platform // string	
gameURL // string	
achievementsWonNoDlc // number	
maxAchievementsNoDlc // number	
achievementsWonIncludeDlc // number
maxAchievementsIncludeDlc // number
gamerScoreWonNoDlc // number
maxGamerScoreNoDlc // number
gamerScoreWonIncludeDlc // number
maxGamerscoreIncludeDlc	// number
trueAchievementWonNoDlc	// number
maxTrueAchievementNoDlc // number
trueAchievementWonIncludeDlc // number
maxTrueAchievementIncludeDlc // number
myCompletionPercentage // number
completionDate // string
challengesWon // number
maxChallenges // number
hoursPlayed // number
myRating // number
siteRating // number
myRatio // number
siteRatio // number
ownershipStatus // string
playStatus // string
format // string
completionEstimate // string
walkthrough // string
gameNotes // stringList
contestStatus // string
```

- platform-uniqueId-index includes gameName

