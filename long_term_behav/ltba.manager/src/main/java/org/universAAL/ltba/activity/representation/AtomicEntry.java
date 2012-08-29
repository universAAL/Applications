package org.universAAL.ltba.activity.representation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.universAAL.ltba.activity.ActivityIntensity;
import org.universAAL.ltba.activity.Room;

public class AtomicEntry {

	private long timestamp;
	private long timeLength;
	private Map<Room, ActivityIntensity> atomicMap;

	public AtomicEntry(long timestamp, long lengthInMillis) {
		this.timestamp = timestamp;
		// System.out.println(new SimpleDateFormat().format(new
		// Date(timestamp)));
		this.timeLength = lengthInMillis;
		atomicMap = new HashMap<Room, ActivityIntensity>();
	}

	public ActivityIntensity putEntry(Room room, ActivityIntensity intensity) {
		ActivityIntensity actInt = atomicMap.put(room, intensity);
		// System.out.println("PUTTING "+room+"--"+intensity);
		// System.out.println("THIS ATOMIC MAP IS"+atomicMap);
		return actInt;

	}

	public ActivityIntensity getEntry(Room room) {
		return atomicMap.get(room);
	}

	/**
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * @return the timeLength
	 */
	public long getTimeLength() {
		return timeLength;
	}

	/**
	 * @return the atomicMap
	 */
	public Map<Room, ActivityIntensity> getAtomicMap() {
		return atomicMap;
	}

	public void setAtomicMap(Map<Room, ActivityIntensity> map) {
		this.atomicMap = new HashMap(map);
	}

	// TODO Avoid deprecated methods
	public String toString() {
		return new String("Atomic Entry at " + new Date(timestamp).getDay()
				+ "-" + new Date(timestamp).getMonth() + "-"
				+ new Date(timestamp).getYear() + " "
				+ new Date(timestamp).getHours() + ":"
				+ new Date(timestamp).getMinutes() + ":"
				+ new Date(timestamp).getSeconds() + " with values: "
				+ atomicMap.toString());

	}

}
