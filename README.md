# TrueAchievements Group Service

![Build Status](https://img.shields.io/badge/Build-Passing-brightgreen)
![Dependencies](https://img.shields.io/badge/Dependencies-Up%20to%20Date-brightgreen)
![Languages](https://img.shields.io/github/languages/top/allenpotts13/nsscapstone_trueachievements?style=flat-square)
[![Coverage](https://img.shields.io/badge/coverage-56%25-brightgreen)](http://localhost:63342/nsscapstone_trueachievements/TrueAchievementsGroupLambda/build/reports/jacoco/test/html/index.html?_ijt=rjaolb59evrglql6aj5266d01m&_ij_reload=RELOAD_ON_SAVE)
![Version](https://img.shields.io/badge/Version-1.0-blue)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-blue?style=flat&logo=linkedin&labelColor=blue)](https://www.linkedin.com/in/allen-potts/)
[![GitHub](https://img.shields.io/badge/GitHub-View_on_GitHub-181717?logo=github)](https://github.com/allenpotts13)
![License](https://img.shields.io/badge/License-MIT-blue)

![AWS CloudFormation Stack Status](https://img.shields.io/badge/CloudFormation-Stack%20Status-blue?logo=amazon-aws)
![EC2 Instances](https://img.shields.io/badge/EC2-Instances-yellow?logo=amazon-aws)
![AWS Lambda](https://img.shields.io/badge/Lambda-Functions-orange?logo=amazon-aws)

## Description
The TrueAchievements Group Service leverages data from your TrueAchievements profile, empowering you to effortlessly create and manage groups for your games. You can organize and categorize games you've played on TrueAchievements, providing  streamlined approach to keeping track of your gaming data. Additionally, the service offers a comprehensive listing of all the games in your library, complete with detailed information about each game and your activity. It enhances your gaming experience by offering a user-friendly way to structure and explore your gaming history. 

## Table of Contents
* [Installation](#installation)
* [Usage](#usage)
* [Technologies](#technologies)
* [Features](#features)
* [Roadmap](#roadmap)
* [Contributors](#contributors)
* [License](#license)
* [Acknowledgements](#acknowledgements)
* [Contact](#contact)

## Installation

### Prerequisites

- **TrueAchievements Pro Account:**
  You need a TrueAchievements Pro account to export your data. If you don't have one, you can register [here](https://www.trueachievements.com/register).

- **Exporting Your Data:**
    1. Log in to your TrueAchievements account.
    2. Click on your name and select "Game collection."
    3. Use the download arrow to save the exported CSV file to your computer. This file will be used to populate your database.

- **Preparing Your Data:**
    1. Open the `csvtojson2.js` file in the `scripts` folder.
    2. Edit the `self.userId` field with your email address.
    3. Run the script (e.g., in Python IDLE) to convert your CSV file to a JSON file (save as `input.json`).

- **Batching Your Data:**
    1. Run the `batchsplit.py` script to break down your data into batches (max 25 games per batch).
    2. Copy the generated directory with batches to your project folder under `data`.

- **Setting Up AWS:**
    - Ensure you have or create an AWS account.
    - The `template.yaml` file will create the DynamoDB table, Lambda functions, and API Gateway.

- **Deploying Your Application:**
    1. Run `sam build --guided` to walk through the deployment process.
    2. Utilize `sam build` and `sam deploy` commands to deploy your application.

- **Deploying Data to DynamoDB:**
    - Run the `newimport.sh` file to write the data to your DynamoDB table using the batch write method.

Follow these steps to set up and deploy your application along with your gaming data.

## Usage

### Dashboard
The Dashboard is the landing page upon starting the application. It provides an overview of your Gamerscore, TrueAchievementsWon, and Completion Percentage. However, no data is displayed, and additional buttons are hidden until you click "Login" and create your account. Once logged in, your data will populate, and you will have access to the "Groups" and "Game Library" buttons.

### All Groups
On this page, you can view all the groups you have created. To create a new group, simply click the "New Group" button. Clicking on a group's name allows you to view its details.

### Group Details
This page displays the details of a selected group, including the group name and games list. Add games to the group by choosing a game from the "Select game to add" dropdown and clicking the "Add Game" button. You can also click on a game's name to view its details. In the "Select game to delete" dropdown, choose a game and click "Delete Game" to remove it from the group.

### Game Details
This page provides detailed information about a selected game, such as the game name, URL (directing to TrueAchievements for more details), and various stats in the "Game Details" section. The "Achievements" section displays the game's achievements, and the "Challenges" section shows any associated challenges. You can also add relevant notes in the "Notes" section.

### Game Library
This page displays all the games in your library, initially limited to five per page. You can adjust the display count as needed. Utilize the search bar to find specific games or click on a game's name to view its details.

## Technologies
* [HTML](https://html.com/)
* [CSS](https://www.w3.org/Style/CSS/Overview.en.html)
* [JavaScript](https://www.javascript.com/)
* [AWS](https://aws.amazon.com/)
* [AWS CloudFormation](https://aws.amazon.com/cloudformation/)
* [AWS Lambda](https://aws.amazon.com/lambda/)
* [AWS DynamoDB](https://aws.amazon.com/dynamodb/)
* [AWS API Gateway](https://aws.amazon.com/api-gateway/)
* [AWS SAM](https://aws.amazon.com/serverless/sam/)
* [AWS CLI](https://aws.amazon.com/cli/)
* [AWS SDK](https://aws.amazon.com/sdk-for-javascript/)
* [AWS Cognito](https://aws.amazon.com/cognito/)
* [AWS S3](https://aws.amazon.com/s3/)
* [AWS CloudFront](https://aws.amazon.com/cloudfront/)
* [AWS Route 53](https://aws.amazon.com/route53/)
* [AWS Certificate Manager](https://aws.amazon.com/certificate-manager/)
* [AWS IAM](https://aws.amazon.com/iam/)
* [AWS CloudWatch](https://aws.amazon.com/cloudwatch/)
* [AWS CodeBuild](https://aws.amazon.com/codebuild/)
* [AWS CodePipeline](https://aws.amazon.com/codepipeline/)
* [AWS CodeDeploy](https://aws.amazon.com/codedeploy/)
* [AWS CodeCommit](https://aws.amazon.com/codecommit/)
* [AWS CodeArtifact](https://aws.amazon.com/codeartifact/)
* [TrueAchievements](https://www.trueachievements.com/)
* [Node.js](https://nodejs.org/en/)
* [NPM](https://www.npmjs.com/)
* [Python](https://www.python.org/)
* [Python IDLE](https://docs.python.org/3/library/idle.html)
* [Visual Studio Code](https://code.visualstudio.com/)
* [IntelliJ IDEA](https://www.jetbrains.com/idea/)
* [Java](https://www.java.com/en/)
* [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
* [Gradle](https://gradle.org/)
* [JUnit](https://junit.org/junit5/)
* [Mockito](https://site.mockito.org/)
* [AWS Toolkit for IntelliJ](https://aws.amazon.com/intellij/)
* [AWS Toolkit for Visual Studio Code](https://aws.amazon.com/visualstudiocode/)
* [AWS CLI](https://aws.amazon.com/cli/)
* [SAM CLI](https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/serverless-sam-cli-install.html)
* [AWS CloudFormation](https://aws.amazon.com/cloudformation/)
* [AWS CloudFormation Designer](https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/working-with-templates-cfn-designer.html)
* [AWS CloudWatch](https://aws.amazon.com/cloudwatch/)
* [AWS CloudWatch Logs](https://aws.amazon.com/cloudwatch/)
* [AWS CodeBuild](https://aws.amazon.com/codebuild/)
* [AWS CodePipeline](https://aws.amazon.com/codepipeline/)
* [AWS CodeDeploy](https://aws.amazon.com/codedeploy/)
* [AWS CodeCommit](https://aws.amazon.com/codecommit/)
* [Docker](https://www.docker.com/)
* [Docker Desktop](https://www.docker.com/products/docker-desktop)

## Features
* Create and manage groups for your games
* Organize and categorize games you've played on TrueAchievements
* Streamlined approach to keeping track of your gaming data
* Comprehensive listing of all the games in your library
* Detailed information about each game and your activity
* User-friendly way to structure and explore your gaming history
* Utilizes data from your TrueAchievements profile
* Export your data from TrueAchievements
* Import your data into the application
* Deploy your application to AWS
* Utilizes AWS Lambda functions
* Utilizes AWS DynamoDB
* Utilizes AWS API Gateway
* Utilizes AWS SAM
* Utilizes AWS CLI
* Utilizes AWS SDK
* Utilizes AWS Cognito
* Utilizes AWS S3
* Utilizes AWS CloudFront

## Roadmap
* Add ability to edit group name
* Add ability to edit game notes
* Add ability to delete group
* Add ability to import data from TrueAchievements

## Contributors
* [Allen Potts](https://github.com/allenpotts13)

## License
This project is licensed under the MIT License - see the [LICENSE.md] 

## Acknowledgements
* [TrueAchievements](https://www.trueachievements.com/)
* [Nashville Software School](https://nashvillesoftwareschool.com/)
  * Jean Soderkvist - Instructor at Nashville Software School - (https://www.linkedin.com/in/jeansoderkvist/)
  * Charlie Penner - Instructor at Nashville Software School - (https://www.linkedin.com/in/charliepenner/)
  * All my fellow classmates at Nashville Software School who helped me along the way!

## Contact
* [Allen Potts](https://github.com/allenpotts13)



