package com.chapter19.enumerated;

import java.util.EnumSet;
import static util.Print.*;
import static com.chapter19.enumerated.AlarmPoints.*;

public class EnumSets {
	public static void main(String[] args) {
		// Empty set
		EnumSet<AlarmPoints> points = EnumSet.noneOf(AlarmPoints.class);
		points.add(BATHROOM);
		print(points);
		points.addAll(EnumSet.of(STAIR1, STAIR2, KITCHEN));
		print(points);
		
		points = EnumSet.allOf(AlarmPoints.class);
		points.removeAll(EnumSet.of(STAIR1, STAIR2, KITCHEN));
		print(points);
		points.removeAll(EnumSet.range(OFFICE1, OFFICE2));
		print(points);
		
		points = EnumSet.complementOf(points);
		print(points);
	}
}
