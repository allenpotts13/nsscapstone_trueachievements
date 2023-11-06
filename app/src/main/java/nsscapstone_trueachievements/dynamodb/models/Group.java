package nsscapstone_trueachievements.dynamodb.models;

import java.util.List;
import java.util.Objects;

public class Group {
    private String groupName;
    private List<Game> gamesList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(groupName, group.groupName) && Objects.equals(gamesList, group.gamesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName, gamesList);
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Game> getGamesList() {
        return gamesList;
    }

    public void setGamesList(List<Game> gamesList) {
        this.gamesList = gamesList;
    }
}
