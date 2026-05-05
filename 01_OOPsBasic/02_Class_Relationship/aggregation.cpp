#include <bits/stdc++.h>
using namespace std;

class Player
{
public:
    string name;

    Player(string name)
    {
        this->name = name;
    }
};

class Team
{
public:
    string teamName;
    vector<Player *> players;

    Team(string teamName)
    {
        this->teamName = teamName;
    }

    void addPlayer(Player *player)
    {
        players.push_back(player);
    }

    void showTeam()
    {
        cout << "Team " << teamName << " has players: " << endl;
        for (Player *p : players)
        {
            cout << " - " << p->name << endl;
        }
    }
};

int main()
{
    Team team("Warrior");

    Player p1("XYZ");
    Player p2("ABC");

    team.addPlayer(&p1);
    team.addPlayer(&p2);

    team.showTeam();

    return 0;
}