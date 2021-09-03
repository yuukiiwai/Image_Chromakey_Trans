package ict;

public class FileMaker {
	String filepre;
	int num;

	public FileMaker(String filepre,int starter) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.filepre = filepre;
		this.num = starter;
	}

	public String getNewReadPath() {
		String newPath;

		newPath = this.filepre + " (" + num +").png";

		return newPath;
	}

	public String getNewWritePath() {
		String newPath;

		newPath = this.filepre + "_res (" + num +").png";
		num++;

		return newPath;
	}
}
