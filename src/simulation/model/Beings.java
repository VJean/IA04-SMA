package simulation.model;

import sim.engine.SimState;
import sim.engine.Stoppable;
import sim.field.grid.ObjectGrid2D;
import sim.field.grid.SparseGrid2D;
import sim.util.Int2D;

public class Beings extends SimState {
	public static int NB_DIRECTIONS = 8;
	public SparseGrid2D yard = new SparseGrid2D(Constants.GRID_SIZE,Constants.GRID_SIZE);
	public Beings(long seed) {
		super(seed);
	}
	public void start() {
		System.out.println("Simulation started");
		super.start();
		yard.clear();
		addAgentsInsects();
		addAgentsFood();
	}
	private void addAgentsInsects() {

		for(int  i  =  0;  i  <  Constants.NUM_INSECT;  i++) {
			Insect insect  =  new Insect();
			Int2D location = getFreeLocation();
			//yard.set(location.x,location.y,a);
			yard.setObjectLocation(insect, location);
			insect.x = location.x;
			insect.y = location.y;
			Stoppable stoppable = schedule.scheduleRepeating(insect);
			insect.stoppable = stoppable;
		}
	}
	private void addAgentsFood() {

		for(int  i  =  0;  i  <  Constants.NUM_FOOD_CELL;  i++) {
			Food f  =  new Food();
			Int2D location = getFreeLocation();
			yard.setObjectLocation(f, location);
			f.x = location.x;
			f.y = location.y;
			Stoppable stoppable = schedule.scheduleRepeating(f);
			f.stoppable = stoppable;
		}
	}

	public boolean isFree(int x, int y) {
		int xx = yard.stx(x);
		int yy = yard.sty(y);
		return yard.getObjectsAtLocation(xx,yy) == null;
	}

	private Int2D getFreeLocation() {
		Int2D location = new Int2D(random.nextInt(yard.getWidth()),
				random.nextInt(yard.getHeight()) );

		while (yard.getObjectsAtLocation(location) != null) {
			location = new Int2D(random.nextInt(yard.getWidth()),
					random.nextInt(yard.getHeight()) );
		}
		return location;
	}
}
