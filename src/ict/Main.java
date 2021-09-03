package ict;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		if(6 != args.length) {
			System.out.println("Image_Chromakey_Trans [png画像名] [個数] [開始番号] [R] [G] [B]");
			System.out.println("\".png\"は記入しないでください");
			System.out.println("windowsの機能で、まとめて選択した状態で改名すると、oooo(n).pngとできます。\n"
					+ "この状態にしてからプログラムを通してください.");

			return;
		}

		// image info
		int width = 0,height = 0;
		int rc,gc,bc;
		int color,r,g,b;

		// image file info
		String innames;
		int length;
		int startNum;
		int index;

		BufferedImage img;
		BufferedImage newImg;

		// manage file-name
		FileMaker file;
		String temppath;

		// read in-line
		try {
			innames = args[0];
			length = Integer.valueOf(args[1]);
			startNum = Integer.valueOf(args[2]);
			rc = Integer.valueOf(args[3]);
			gc = Integer.valueOf(args[4]);
			bc = Integer.valueOf(args[5]);
		}catch(NumberFormatException e) {
			System.out.println(e);
			return ;
		}

		file = new FileMaker(innames, startNum);
		newImg = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
		// read images
		// process all-images
		for(index = 0;index < length;index++) {
			temppath = file.getNewReadPath();
			try {
				img = ImageIO.read(new File(temppath));
			}catch(IOException e) {
				System.out.println(e);
				return;
			}
			if(index == 0) {
				width = img.getWidth();
				height = img.getHeight();
				newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
				System.out.println(""+width+","+height);
			}

			// process each-image
			for(int x = 0; x < width ; x++) {
				for(int y = 0 ; y < height ; y++) {

					color = img.getRGB(x, y);
					r = ( color >> 16 ) & 0xff;
					g = ( color >> 8 ) & 0xff;
					b = color & 0xff;
					if((rc - 20 < r && r < rc + 20) && (gc - 20 < g && g < gc + 20) && (bc - 20 < b && b < bc + 20)) {
						newImg.setRGB(x, y, 0);
					}else {
						newImg.setRGB(x, y, color);
					}
				}
			}

			try {
				ImageIO.write(newImg, "PNG", new File(file.getNewWritePath()));
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

}
