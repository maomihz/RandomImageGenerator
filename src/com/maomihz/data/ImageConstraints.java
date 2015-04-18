package com.maomihz.data;

public class ImageConstraints {
	public int width, height, pixelWidth, pixelHeight, number;
	public String name="random";
	public boolean grayScale = false;

	public ImageConstraints() {

	}

	public ImageConstraints(int width, int height, int pixelWidth,
			int pixelHeight, int number, String name, boolean grayScale) {
		super();
		this.width = width;
		this.height = height;
		this.pixelWidth = pixelWidth;
		this.pixelHeight = pixelHeight;
		this.number = number;
		this.name = name;
		this.grayScale = grayScale;
	}

}
