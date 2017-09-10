package entity;

public class Rank {
	private int id;
	private String rankname;
	public Rank() {
		super();
	}
	public Rank(int id, String rankname) {
		super();
		this.id = id;
		this.rankname = rankname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRankname() {
		return rankname;
	}
	public void setRankname(String rankname) {
		this.rankname = rankname;
	}
}
