package com.closedcircles.reloaded;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by lenik on 12/29/15.
 */
public class FontLoader {

	public static final int MESSAGES_FONT = 0;
	public static final int ROBOTO_ITALIC = 1;

	private static final int NUM_OF_CUSTOM_FONTS = 2;

	private static boolean fontsLoaded = false;

	private static Typeface[] fonts = new Typeface[NUM_OF_CUSTOM_FONTS];

	private static String[] fontPath = {
			"fonts/Messages.ttf",
			"fonts/Roboto-LightItalic.ttf",
	};

	public static Typeface getTypeface(Context context, int fontIdentifier) {
		if (!fontsLoaded) {
			loadFonts(context);
		}
		return fonts[fontIdentifier];
	}


	private static void loadFonts(Context context) {
		for (int i = 0; i < NUM_OF_CUSTOM_FONTS; i++) {
			fonts[i] = Typeface.createFromAsset(context.getAssets(), fontPath[i]);
		}
		fontsLoaded = true;
	}
}
