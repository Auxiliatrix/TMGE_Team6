package tmge.game.base;

public class GameEnvironment {

	public Board state;
	public double score;
	
	public Player player;
	
	public GameEnvironment(Board initialState, Player player) {
		state = initialState;
		score = 0;
	}
	
	public void update() {
		score += state.update();
	}
	
}
