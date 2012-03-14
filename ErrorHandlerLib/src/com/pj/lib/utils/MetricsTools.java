package com.pj.lib.utils;

import android.content.Context;

public class MetricsTools {
	public static float pixelsToSp(Context context, Float px) {
	    float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
	    return px/scaledDensity;
	}
	
	public static float spToPixels(Context context, Float sp) {
		float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
	    return scaledDensity*sp; 
	}
}
