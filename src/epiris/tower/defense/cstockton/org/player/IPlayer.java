package epiris.tower.defense.cstockton.org.player;

public interface IPlayer
{
    public IPlayer addGold(final int pGold);

    public IPlayer deductGold(final int pGold);

    public IPlayer setGold(final int pGold);

    public int getGold();

    public IPlayer addGamePoints(final int pGamePoints);

    public IPlayer deductGamePoints(final int pGamePoints);

    public IPlayer setGamePoints(final int pGamePoints);

    public int getGamePoints();

    public IPlayer addLives(final int pLives);

    public IPlayer deductLives(final int pLives);

    public IPlayer setLives(final int pLives);

    public int getLives();

    public IPlayer addScore(final int pScore);

    public IPlayer deductScore(final int pScore);

    public IPlayer setScore(final int pScore);

    public int getScore();

    public IPlayer addDifficulty(final int pDifficulty);

    public IPlayer deductDifficulty(final int pDifficulty);

    public IPlayer setDifficulty(final int pDifficulty);

    public int getDifficulty();

}