package com.ramayan.model;
import java.util.HashMap;
import java.util.Map;

public final class Forest {

	private final Map<Integer, Map<Integer, Spot>> map = new HashMap<Integer, Map<Integer, Spot>>();

	private Forest() {
	}

	private void putSpot(int x, int y, Spot spot) {
		if (!map.containsKey(x)) {
			map.put(x, new HashMap<Integer, Spot>());
		}
		map.get(x).put(y, spot);
	}

	public static Forest newInstance() {
		Forest dungeon = new Forest();
		dungeon.putSpot(0, 0, Spot.newRegularInstance(8));
		dungeon.putSpot(-1, 1, Spot.newRegularInstance(0));
		dungeon.putSpot(0, 1, Spot.newRegularInstance(1));
		dungeon.putSpot(1, 1, Spot.newRegularInstance(2));
		dungeon.putSpot(-1, 2, Spot.newRegularInstance(3));
		dungeon.putSpot(1, 2, Spot.newRegularInstance(4));
		dungeon.putSpot(-1, 3, Spot.newRegularInstance(5));
		dungeon.putSpot(0, 3, Spot.newRegularInstance(6));
		dungeon.putSpot(1, 3, Spot.newRegularInstance(7));
		dungeon.putSpot(0, 4, Spot.newRavanInstance());
		return dungeon;
	}

	public boolean spotExist(int x, int y) {
		if (!map.containsKey(x)) {
			return false;
		}
		return map.get(x).containsKey(y);
	}

	public Spot getSpot(int x, int y) {
		return map.get(x).get(y);
	}

}