import csv
import json
import uuid

class Game:
    def __init__(self, gameName, platform, gameURL, achievementsWonNoDlc,
                 maxAchievementsNoDlc, achievementsWonIncludeDlc, maxAchievementsIncludeDlc,
                 gamerScoreWonNoDlc, maxGamerScoreNoDlc, gamerScoreWonIncludeDlc,
                 maxGamerScoreIncludeDlc, trueAchievementWonNoDlc, maxTrueAchievementNoDlc,
                 trueAchievementWonIncludeDlc, maxTrueAchievementIncludeDlc, myCompletionPercentage,
                 completionDate, challengesWon, maxChallenges, hoursPlayed, myRating, siteRating,
                 myRatio, siteRatio, ownershipStatus, playStatus, format, completionEstimate,
                 walkthrough, gameNotes, contestStatus):
        self.uniqueId = str(uuid.uuid4())  # Generate a new UUID
        self.userId = "allenpotts13@gmail.com"  # New field
        self.gameName = gameName.strip()
        self.platform = platform
        self.gameURL = gameURL
        self.achievementsWonNoDlc = int(achievementsWonNoDlc) if achievementsWonNoDlc else None
        self.maxAchievementsNoDlc = int(maxAchievementsNoDlc) if maxAchievementsNoDlc else None
        self.achievementsWonIncludeDlc = int(achievementsWonIncludeDlc) if achievementsWonIncludeDlc else None
        self.maxAchievementsIncludeDlc = int(maxAchievementsIncludeDlc) if maxAchievementsIncludeDlc else None
        self.gamerScoreWonNoDlc = int(gamerScoreWonNoDlc) if gamerScoreWonNoDlc else None
        self.maxGamerScoreNoDlc = int(maxGamerScoreNoDlc) if maxGamerScoreNoDlc else None
        self.gamerScoreWonIncludeDlc = int(gamerScoreWonIncludeDlc) if gamerScoreWonIncludeDlc else None
        self.maxGamerScoreIncludeDlc = int(maxGamerScoreIncludeDlc) if maxGamerScoreIncludeDlc else None
        self.trueAchievementWonNoDlc = int(trueAchievementWonNoDlc) if trueAchievementWonNoDlc else None
        self.maxTrueAchievementNoDlc = int(maxTrueAchievementNoDlc) if maxTrueAchievementNoDlc else None
        self.trueAchievementWonIncludeDlc = int(trueAchievementWonIncludeDlc) if trueAchievementWonIncludeDlc else None
        self.maxTrueAchievementIncludeDlc = int(maxTrueAchievementIncludeDlc) if maxTrueAchievementIncludeDlc else None
        self.myCompletionPercentage = int(myCompletionPercentage) if myCompletionPercentage else None
        self.completionDate = completionDate
        self.challengesWon = int(challengesWon) if challengesWon else None
        self.maxChallenges = int(maxChallenges) if maxChallenges else None
        self.hoursPlayed = int(hoursPlayed) if hoursPlayed else None
        self.myRating = int(myRating) if myRating else None
        self.siteRating = float(siteRating) if siteRating else None
        self.myRatio = float(myRatio) if myRatio else None
        self.siteRatio = float(siteRatio) if siteRatio else None
        self.ownershipStatus = ownershipStatus
        self.playStatus = playStatus
        self.format = format
        self.completionEstimate = completionEstimate
        self.walkthrough = walkthrough
        self.gameNotes = [f"Empty Note {gameName}"] if not gameNotes else list(gameNotes)
        self.contestStatus = contestStatus

    def to_batch_write_item(self):
        def convert_to_dynamo_type(value):
            if isinstance(value, str):
                return {"S": value}
            elif isinstance(value, (int, float)):
                return {"N": str(value)}
            elif isinstance(value, list):
                return {"L": [convert_to_dynamo_type(item) for item in value]}
            elif isinstance(value, bool):
                return {"BOOL": value}
            elif value is None:
                return {"NULL": True}
            else:
                raise TypeError(f"Unsupported type: {type(value)}")

        item = {key: convert_to_dynamo_type(value) for key, value in self.__dict__.items()}

        put_request = {
            "PutRequest": {
                "Item": item
            }
        }
        return put_request

# Provide the correct file path for your CSV file
csv_file_path = r'C:\Users\curse\OneDrive\Documents\MyGameCollection_ 191123_013948.csv'

games_list = []

with open(csv_file_path, mode='r', encoding='utf-8-sig') as csv_file:
    csv_reader = csv.DictReader(csv_file)
    for row in csv_reader:
        # Create an instance of your Game class for each row in the CSV
        game_instance = Game(
            gameName=row.get(' Game name') or row.get('Game name'),
            platform=row.get('Platform'),
            gameURL=row.get('Game URL'),
            achievementsWonNoDlc=row.get('Achievements Won (No DLC)'),
            maxAchievementsNoDlc=row.get('Max Achievements (No DLC)'),
            achievementsWonIncludeDlc=row.get('Achievements Won (incl. DLC)'),
            maxAchievementsIncludeDlc=row.get('Max Achievements (incl. DLC)'),
            gamerScoreWonNoDlc=row.get('GamerScore Won (No DLC)'),
            maxGamerScoreNoDlc=row.get('Max GamerScore (No DLC)'),
            gamerScoreWonIncludeDlc=row.get('GamerScore Won (incl. DLC)'),
            maxGamerScoreIncludeDlc=row.get('Max Gamerscore (incl. DLC)'),
            trueAchievementWonNoDlc=row.get('TrueAchievement Won (No DLC)'),
            maxTrueAchievementNoDlc=row.get('Max TrueAchievement (No DLC)'),
            trueAchievementWonIncludeDlc=row.get('TrueAchievement Won (incl. DLC)'),
            maxTrueAchievementIncludeDlc=row.get('Max TrueAchievement (incl. DLC)'),
            myCompletionPercentage=row.get('My Completion Percentage'),
            completionDate=row.get('Completion Date'),
            challengesWon=row.get('Challenges Won'),
            maxChallenges=row.get('Max Challenges'),
            hoursPlayed=row.get('Hours Played'),
            myRating=row.get('My Rating'),
            siteRating=row.get('Site Rating'),
            myRatio=row.get('My Ratio'),
            siteRatio=row.get('Site Ratio'),
            ownershipStatus=row.get('Ownership Status'),
            playStatus=row.get('Play Status'),
            format=row.get('Format'),
            completionEstimate=row.get('Completion Estimate'),
            walkthrough=row.get('Walkthrough'),
            gameNotes=row.get('Game Notes'),  # Assuming 'Game Notes' is the column for notes in your CSV
            contestStatus=row.get('ContestStatus')
        )
        games_list.append(game_instance)

# Convert the list of Game instances to BatchWriteItem format
batch_write_item_list = [game.to_batch_write_item() for game in games_list]

# Create the final JSON format
output_json = {"games": batch_write_item_list}

# Print or use the final JSON as needed
print(json.dumps(output_json, indent=2))


